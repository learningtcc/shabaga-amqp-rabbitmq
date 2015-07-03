package com.shagaba.amqp.config;

import com.shagaba.amqp.infra.QueueBinding;


public enum ShagabaQueueBinding implements QueueBinding {
	FRONTEND_SYNC_REQUEST(ShagabaExchange.LAB, ShagabaQueue.FRONTEND_SYNC_REQUEST),
	BACKEND_SYNC_PORTAL_CONFIG(ShagabaExchange.LAB, ShagabaQueue.BACKEND_SYNC_PORTAL_CONFIG),
	BACKEND_SYNC_STATUS(ShagabaExchange.LAB, ShagabaQueue.BACKEND_SYNC_STATUS),
	
	FRONTEND_GUESTS_REQUEST(ShagabaExchange.LAB, ShagabaQueue.FRONTEND_GUESTS_REQUEST),
	FRONTEND_GUEST_REQUEST(ShagabaExchange.LAB, ShagabaQueue.FRONTEND_GUEST_REQUEST),
	FRONTEND_SAVE_GUEST_REQUEST(ShagabaExchange.LAB, ShagabaQueue.FRONTEND_SAVE_GUEST_REQUEST),

	FRONTEND_LONGER_REQUEST(ShagabaExchange.LAB, ShagabaQueue.FRONTEND_LONGER_REQUEST),
	BACKEND_LONGER_DATA(ShagabaExchange.LAB, ShagabaQueue.BACKEND_LONGER_DATA)
	;
	
	private String exchange;
	private String queue;
	private String key;

	/**
	 * @param exchange
	 * @param queue
	 * @param key
	 */
	private ShagabaQueueBinding(String exchange, String queue, String key) {
		this.exchange = exchange;
		this.queue = queue;
		this.key = key;
	}
	/**
	 * @param exchange
	 * @param queue
	 */
	private ShagabaQueueBinding(String exchange, String queue) {
		this.exchange = exchange;
		this.queue = queue;
		this.key = queue;
	}
	/**
	 * @return the exchange
	 */
	@Override
	public String getExchange() {
		return exchange;
	}
	/**
	 * @return the queue
	 */
	@Override
	public String getQueue() {
		return queue;
	}
	/**
	 * @return the key
	 */
	@Override
	public String getKey() {
		return key;
	}

	
}
