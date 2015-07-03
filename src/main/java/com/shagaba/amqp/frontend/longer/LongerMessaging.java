package com.shagaba.amqp.frontend.longer;

import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.shagaba.amqp.common.domain.Longer;
import com.shagaba.amqp.common.support.ShagabaMessageHeaders;
import com.shagaba.amqp.common.support.UuidMessagePostProcessor;
import com.shagaba.amqp.config.ShagabaQueue;
import com.shagaba.amqp.infra.cache.TtlCache;

@Component
public class LongerMessaging {
	
//	private long messagingCacheMaximumSize = 5000;
//	
//	private long messagingExpireAfterWrite = 70;
//	
//	@PostConstruct
//	public void init() {
//		super.initMessagingCache();
//	}
//	
//	@Autowired
//	private AmqpTemplate amqpTemplate;
//	
//	@Override
//	protected long getCacheMaximumSize() {
//		return messagingCacheMaximumSize;
//	}
//
//	@Override
//	protected long getCacheExpireAfterWrite() {
//		return messagingExpireAfterWrite;
//	}
//	
//	public void sendLongerRequest(Long id, Date date) {
//		System.out.println("frontend sends longer -> " + id);
//		UUID uuidMessagingCacheKey = super.put(date);
//		System.out.println("frontend sends uuidMessagingCacheKey -> " + uuidMessagingCacheKey);
//		amqpTemplate.convertAndSend(ShagabaQueue.FRONTEND_LONGER_REQUEST, id, new UuidMessagePostProcessor(uuidMessagingCacheKey));
//	}
//	
//	@RabbitListener(queues = { ShagabaQueue.BACKEND_LONGER_DATA })
//	public void handleLongerData(Message<Longer> incomingMessage) {
//		UUID uuid = UUID.fromString(incomingMessage.getHeaders().get(ShagabaMessageHeaders.SHAGABA_UUID_MESSAGING_KEY, String.class));
//
//		System.out.println(ShagabaQueue.BACKEND_LONGER_DATA + " <- " + uuid);
//		System.out.println(ShagabaQueue.BACKEND_LONGER_DATA + " <- " + incomingMessage.getPayload());
//		
//		System.out.println(ShagabaQueue.BACKEND_LONGER_DATA + " cache <- " + super.getIfPresent(uuid));
//		
//	}
//
}
