package com.shagaba.amqp.backend.guest;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.shagaba.amqp.common.domain.Guest;
import com.shagaba.amqp.config.ShagabaQueue;


@Component
public class GuestRequestListener {
	
	@RabbitListener(queues = { ShagabaQueue.FRONTEND_GUEST_REQUEST })
	public Guest handleFrontendGuestRequestMessage(Message<Long> incomingMessage) {
		Long id = incomingMessage.getPayload();
		Guest guest = new Guest("Im Guest No' " + id);
		System.out.println("backend replies -> " + guest);
		return guest;
	}
	
	@RabbitListener(queues = { ShagabaQueue.FRONTEND_SAVE_GUEST_REQUEST })
	public Guest handleFrontendSaveGuestRequestMessage(Message<Guest> incomingMessage) {
		Guest guest = new Guest(incomingMessage.getPayload().getContent() + " - saved");
		System.out.println("backend replies -> " + guest);
		return guest;
	}

	
}
