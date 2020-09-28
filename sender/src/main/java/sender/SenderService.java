package sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SenderService  {

	private final RabbitTemplate rabbitTemplate;

	public SenderService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMsg(String msg) {
		rabbitTemplate.convertAndSend(QueueSenderConfig.topicExchangeName, "foo.bar.baz", msg);
	}
	
	public void sendMsgTo(String reciverName,String msg) {
		rabbitTemplate.convertAndSend(reciverName, "foo.bar.baz", msg);
	}
	
	public void sendMsgToFanout(String reciverName,String msg) {
		rabbitTemplate.convertAndSend(reciverName, msg);
	}
	
	public void sendMsgToDirect(String reciverName,String msg) {
		rabbitTemplate.convertAndSend(reciverName,"agazin-direct", msg);
	}
	public void sendToNode(String msg) {
//		rabbitTemplate.setExchange("logs");
		rabbitTemplate.convertAndSend("logs","sss", msg);
	}

}
