package com.shagaba.amqp.backend.sync;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shagaba.amqp.common.domain.PortalConfig;
import com.shagaba.amqp.common.domain.Status;
import com.shagaba.amqp.config.ShagabaQueue;

@Service
public class SyncReplaySender {

	@Autowired
	AmqpTemplate amqpTemplate;
	
	public void sendReplay(PortalConfig portalConfig) {
		System.out.println("backend replies -> " + portalConfig);
		amqpTemplate.convertAndSend(ShagabaQueue.BACKEND_SYNC_PORTAL_CONFIG, portalConfig);
	}
	
	public void sendReplay(Status status) {
		System.out.println("backend replies -> " + status);
		amqpTemplate.convertAndSend(ShagabaQueue.BACKEND_SYNC_STATUS, status);
	}
}

