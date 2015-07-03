package com.shagaba.amqp.common.support;

import java.util.UUID;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

public class UuidMessagePostProcessor implements MessagePostProcessor{
	
	private UUID uuidMessagingCacheKey;
	
	public UuidMessagePostProcessor(UUID uuidMessagingCacheKey) {
		this.uuidMessagingCacheKey = uuidMessagingCacheKey;
	}

	@Override
	public Message postProcessMessage(Message message) throws AmqpException {
		
		message.getMessageProperties().setHeader(ShagabaMessageHeaders.SHAGABA_UUID_MESSAGING_KEY, uuidMessagingCacheKey);
		return message;
	}

}
