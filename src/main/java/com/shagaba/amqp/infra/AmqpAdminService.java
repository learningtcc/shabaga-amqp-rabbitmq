package com.shagaba.amqp.infra;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;

public class AmqpAdminService {
	
	protected AmqpAdmin amqpAdmin;
	protected AmqpTopology amqpTopology;
	
	private AmqpAdminService(AmqpAdmin amqpAdmin, AmqpTopology amqpTopology)	{
		this.amqpAdmin = amqpAdmin;
		this.amqpTopology = amqpTopology;
	}
	
	public void amqpConstruct() {
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
	}

	
	public void amqpPurgeAllQueues() {
		// Queue
		for (Queue queue : this.amqpTopology.getAmqpQueueEntries()) {
			this.amqpAdmin.purgeQueue(queue.getName(), true);
		}
	}

	public void amqpPurgeExchangeQueues(String exchange) {
		// Queue
		for (Queue queue : this.amqpTopology.getAmqpQueueEntries(exchange)) {
			this.amqpAdmin.purgeQueue(queue.getName(), true);
		}
	}

	
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

}
