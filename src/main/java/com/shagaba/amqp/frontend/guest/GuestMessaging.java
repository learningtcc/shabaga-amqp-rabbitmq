package com.shagaba.amqp.frontend.guest;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shagaba.amqp.common.domain.Guest;
import com.shagaba.amqp.config.ShagabaQueue;

@Component
public class GuestMessaging {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public List<Guest> getAllGuests(Long size) {
		System.out.println("frontend sends AllGuests -> " + size);
		List<Guest> incommingGuests = (List<Guest>) amqpTemplate.convertSendAndReceive(ShagabaQueue.FRONTEND_GUESTS_REQUEST, size);
		// TODO something
		System.out.println(ShagabaQueue.FRONTEND_GUESTS_REQUEST + " <- " + incommingGuests);
		return incommingGuests;
	}
	
	public Guest getGuestById(Long id) {
		System.out.println("frontend sends GuestById -> " + id);
		Guest incomingGuest = (Guest) amqpTemplate.convertSendAndReceive(ShagabaQueue.FRONTEND_GUEST_REQUEST, id);
		// TODO something
		System.out.println(ShagabaQueue.FRONTEND_GUEST_REQUEST + " <- " + incomingGuest);
		return incomingGuest;
	}
	
	public Guest createGuest(Guest guest) {
		System.out.println("frontend sends createGuest -> " + guest);
		Guest incomingGuest = (Guest) amqpTemplate.convertSendAndReceive(ShagabaQueue.FRONTEND_SAVE_GUEST_REQUEST, guest);
		// TODO something
		System.out.println(ShagabaQueue.FRONTEND_SAVE_GUEST_REQUEST + " <- " + incomingGuest);
		return incomingGuest;
	}
	
	public Guest updateGuest(Guest guest) {
		System.out.println("frontend sends updateGuest -> " + guest);
		Guest incomingGuest = (Guest) amqpTemplate.convertSendAndReceive(ShagabaQueue.FRONTEND_SAVE_GUEST_REQUEST, guest);
		// TODO something
		System.out.println(ShagabaQueue.FRONTEND_SAVE_GUEST_REQUEST + " <- " + incomingGuest);
		return incomingGuest;
	}
}
