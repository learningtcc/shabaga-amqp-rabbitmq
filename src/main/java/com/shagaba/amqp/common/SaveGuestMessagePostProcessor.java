package com.shagaba.amqp.common;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

public class SaveGuestMessagePostProcessor implements MessagePostProcessor {
	
	public enum SaveMode{SAVE, UPDATE};
	
	private SaveMode saveMode;
	
	/**
	 * @param saveMode
	 */
	public SaveGuestMessagePostProcessor(SaveMode saveMode) {
		this.saveMode = saveMode;
		System.out.println("SLEEP = " + saveMode);
	}

	@Override
	public Message postProcessMessage(Message message) throws AmqpException {
		try {
			message.getMessageProperties().setHeader("SLEEP", saveMode);
		} catch (Exception cause) {
			throw new AmqpException(cause);
		}
		return message;
	}

}
