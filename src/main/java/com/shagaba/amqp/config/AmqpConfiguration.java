package com.shagaba.amqp.config;

import java.util.Collection;

import javax.annotation.PreDestroy;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.shagaba.amqp.infra.AmqpTopology;

@Configuration
@EnableRabbit
@PropertySource("classpath:amqp.properties")
@ComponentScan(basePackages = { "com.shagaba.amqp" })
public class AmqpConfiguration {
	
	private AmqpTopology amqpTopology;
	private AmqpAdmin amqpAdmin;
	
	@Value("${rabbit.hostname}")
	private String hostname;

	@Value("${rabbit.username}")
	private String username;

	@Value("${rabbit.password}")
	private String password;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(hostname);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		return connectionFactory;
	}
	
	@Bean
	public AmqpTopology amqpTopology() {
		return new AmqpTopology(ShagabaQueueBinding.values());
	}

	/**
	 * set up the queue, exchange, binding on the broker
	 * @return
	 */
	@Bean
	public AmqpAdmin amqpAdmin() {
		this.amqpAdmin = new RabbitAdmin(connectionFactory());
		this.amqpTopology = amqpTopology();

		// Exchange
		for (Exchange exchange : this.amqpTopology.getAmqpExchangeEntries()) {
			this.amqpAdmin.declareExchange(exchange);
		}
			
		// Queue
		for (Queue queue : this.amqpTopology.getAmqpQueueEntries()) {
			this.amqpAdmin.declareQueue(queue);
		}
		
		// Binding
		for (Binding binding : this.amqpTopology.getAmqpBindingEntries()) {
			this.amqpAdmin.declareBinding(binding);
		}
		return this.amqpAdmin;
	}
	
	@PreDestroy
	public void amqpDestroy() {
		// Binding
		for (Binding binding : this.amqpTopology.getAmqpBindingEntries()) {
			this.amqpAdmin.removeBinding(binding);
		}
		
		// Queue
		for (Queue queue : this.amqpTopology.getAmqpQueueEntries()) {
			this.amqpAdmin.deleteQueue(queue.getName());
		}
		
		// Exchange
		for (Exchange exchange : this.amqpTopology.getAmqpExchangeEntries()) {
			this.amqpAdmin.deleteExchange(exchange.getName());
		}
	}

	@Bean
	public ObjectMapper jacksonObjectMapper() {
		return new ObjectMapper()
				.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
				.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)

				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

				.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)
				.findAndRegisterModules();
	}

	@Bean
	public Jackson2JsonMessageConverter jsonMessageConverter() {
		Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
		converter.setJsonObjectMapper(jacksonObjectMapper());
		return converter;
	}

	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate();
		template.setConnectionFactory(connectionFactory);
		template.setMessageConverter(jsonMessageConverter());
//		template.setReplyTimeout(60000L);
		return template;
	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(jsonMessageConverter());
//		factory.setConcurrentConsumers(5);
//		factory.setMaxConcurrentConsumers(10);
//		factory.setPrefetchCount(100);
//		factory.setReceiveTimeout(60000L);
		return factory;
	}
	
}
