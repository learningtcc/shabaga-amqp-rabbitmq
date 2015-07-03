package com.shagaba.amqp.backend.longer;

import java.util.UUID;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.shagaba.amqp.common.domain.Longer;
import com.shagaba.amqp.common.support.ShagabaMessageHeaders;
import com.shagaba.amqp.config.ShagabaQueue;

@Component
public class LongerMessagingListener {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Autowired
	private LongerSender longerSender;
	
	@RabbitListener(queues = { ShagabaQueue.FRONTEND_LONGER_REQUEST })
	public void handleLongerMessagingRequest(Message<Long> incomingMessage) {
		UUID uuid = UUID.fromString(incomingMessage.getHeaders().get(ShagabaMessageHeaders.SHAGABA_UUID_MESSAGING_KEY, String.class));
		
		System.out.println(ShagabaQueue.FRONTEND_LONGER_REQUEST + " <- " + uuid);
		System.out.println(ShagabaQueue.FRONTEND_LONGER_REQUEST + " <- " + incomingMessage.getPayload());

		longerSender.sendLongerData(new Longer("bla bla " + incomingMessage.getPayload()), uuid);
	}

}
