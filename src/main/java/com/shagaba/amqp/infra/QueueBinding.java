package com.shagaba.amqp.infra;

public interface QueueBinding {

	/**
	 * @return the exchange
	 */
	public String getExchange();
	/**
	 * @return the queue
	 */
	public String getQueue();
	/**
	 * @return the key
	 */
	public String getKey();

}
