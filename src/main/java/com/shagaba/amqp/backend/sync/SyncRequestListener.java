package com.shagaba.amqp.backend.sync;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.shagaba.amqp.common.domain.PortalConfig;
import com.shagaba.amqp.common.domain.Status;
import com.shagaba.amqp.common.domain.SyncType;
import com.shagaba.amqp.config.ShagabaQueue;


@Component
public class SyncRequestListener {
	
	@Autowired
	private SyncReplaySender syncReplaySender;

	@RabbitListener(queues = { ShagabaQueue.FRONTEND_SYNC_REQUEST })
	public void handleMessage(Message<SyncType> incomingMessage) {
		SyncType syncType = incomingMessage.getPayload();
		
		if (syncType == SyncType.ALL || syncType == SyncType.PORTAL_CONFIG) {
			// TODO load portal config
			PortalConfig portalConfig = new PortalConfig("1st option");
			syncReplaySender.sendReplay(portalConfig);
		}
		
		if (syncType == SyncType.ALL || syncType == SyncType.STATUS) {
			// TODO load status
			Status status = new Status("1st option");
			syncReplaySender.sendReplay(status);
		}
	}

}
