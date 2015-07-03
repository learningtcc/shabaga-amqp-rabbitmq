package com.shagaba.amqp.frontend.sync;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.shagaba.amqp.common.domain.PortalConfig;
import com.shagaba.amqp.common.domain.Status;
import com.shagaba.amqp.common.domain.SyncType;
import com.shagaba.amqp.config.ShagabaQueue;

@Component
public class SyncMessaging {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void sendSyncRequest(SyncType syncType) {
		System.out.println("frontend sends sync -> " + syncType);
		amqpTemplate.convertAndSend(ShagabaQueue.FRONTEND_SYNC_REQUEST, syncType);
	}

	@RabbitListener(queues = { ShagabaQueue.BACKEND_SYNC_PORTAL_CONFIG })
	public void handlePortalConfigMessage(Message<PortalConfig> incomingMessage) {
		// TODO update portal config
		System.out.println(ShagabaQueue.BACKEND_SYNC_PORTAL_CONFIG + " <- " + incomingMessage.getPayload());
	}

	@RabbitListener(queues = { ShagabaQueue.BACKEND_SYNC_STATUS })
	public void handleStatusMessage(Message<Status> incomingMessage) {
		// TODO update status
		System.out.println(ShagabaQueue.BACKEND_SYNC_STATUS + " <- " + incomingMessage.getPayload());
	}


}
