package com.shagaba.amqp.backend.longer;

import java.util.UUID;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shagaba.amqp.common.domain.Longer;
import com.shagaba.amqp.common.support.UuidMessagePostProcessor;
import com.shagaba.amqp.config.ShagabaQueue;

@Service
public class LongerSender {

	@Autowired
	AmqpTemplate amqpTemplate;
	
	public void sendLongerData(Longer longer, UUID uuidMessagingCacheKey) {
		System.out.println("backend sends longer -> " + longer);
		amqpTemplate.convertAndSend(ShagabaQueue.BACKEND_LONGER_DATA, longer, new UuidMessagePostProcessor(uuidMessagingCacheKey));
	}
}

