package com.shagaba.amqp.infra;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class AmqpTopology {
	
	private Multimap<Exchange, Queue> topologyMap;
	
	private Map<String, Binding> bindingMap;
	private Map<String, Exchange> exchangeMap;
	private Map<String, Queue> queueMap;
	
	
	/**
	 * 
	 * @param queueBindingArray
	 */
	public AmqpTopology(QueueBinding[] queueBindingArray) {
		this.topologyMap = ArrayListMultimap.create();
		
		this.bindingMap = new HashMap<>();
		this.exchangeMap = new HashMap<>();
		this.queueMap = new HashMap<>();
		
		for (QueueBinding queueBinding : queueBindingArray) {
			// Queue
			Queue queue = new Queue(queueBinding.getQueue());

			// Exchange
			TopicExchange exchange = (TopicExchange) exchangeMap.get(queueBinding.getExchange());
			if (exchange == null) {
				exchange = new TopicExchange(queueBinding.getExchange());
				this.exchangeMap.put(queueBinding.getExchange(), exchange);
			}
			// Binding key
			Binding binding = BindingBuilder.bind(queue).to(exchange).with(queueBinding.getKey());
			
			this.bindingMap.put(queueBinding.getKey(), binding);
			this.queueMap.put(queueBinding.getQueue(), queue);
			this.topologyMap.put(exchange, queue);
		}
	}

	/**
	 * @return the amqpExchangeEntries
	 */
	public Collection<Exchange> getAmqpExchangeEntries() {
		return this.exchangeMap.values();
	}

	/**
	 * @return the amqpQueueEntries
	 */
	public Collection<Queue> getAmqpQueueEntries() {
		return this.queueMap.values();
	}

	/**
	 * @return the amqpBindingEntries
	 */
	public Collection<Binding> getAmqpBindingEntries() {
		return this.bindingMap.values();
	}

	/**
	 * @param exchange
	 * @return the amqpQueueEntries
	 */
	public Collection<Queue> getAmqpQueueEntries(String exchange) {
		return getAmqpQueueEntries(exchangeMap.get(exchange));
	}
	
	/**
	 * @param exchange
	 * @return the amqpQueueEntries
	 */
	public Collection<Queue> getAmqpQueueEntries(Exchange exchange) {
		return this.topologyMap.get(exchange);
	}
	
	/**
	 * @param exchange
	 * @return
	 */
	public Exchange getExchange(String exchange) {
		return this.exchangeMap.get(exchange);
	}

	/**
	 * @param queue
	 * @return
	 */
	public Queue getQueue(String queue) {
		return this.queueMap.get(queue);
	}

	/**
	 * @param binding
	 * @return
	 */
	public Binding getBinding(String binding) {
		return bindingMap.get(binding);
	}

}
