package com.agazin;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	@RabbitListener(queues = QueueReceiverConfig.queueName)
	public void receive(Message receiveMessage) {
		System.out.println(" @RabbitListener >>>>>>>>>>>>>>>>> " + receiveMessage);
	}
	
	@RabbitListener(queues = "agazin")
	public void receiveAgazin(Message receiveMessage) {
		System.out.println(" @RabbitListener[AGAZIN] >>>>>>>>>>>>>>>>> " + receiveMessage);
	}

}
