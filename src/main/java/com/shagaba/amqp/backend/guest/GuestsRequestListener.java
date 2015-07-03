package com.shagaba.amqp.backend.guest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.shagaba.amqp.common.domain.Guest;
import com.shagaba.amqp.config.ShagabaQueue;


@Component
public class GuestsRequestListener {
	
	@RabbitListener(queues = { ShagabaQueue.FRONTEND_GUESTS_REQUEST })
	public List<Guest> handleMessage(Message<Long> incomingMessage) {
		Long size = incomingMessage.getPayload();

		// TODO load guests
		List<Guest> guests = new ArrayList<>();
		for (long i = 1; i <= size; ++i) {
			guests.add(new Guest("Im Guest No' " + i));
		}
		System.out.println("backend replies -> " + guests);
		return guests;
	}

}
