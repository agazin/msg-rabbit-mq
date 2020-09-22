package sender;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg/api")
public class SenderController {
	private SenderService sender;
	public SenderController(SenderService sender) {
		this.sender = sender;
	}
	
	@PostMapping("/send/hello")
	public void sendHello() {
		sender.sendMsg("hello");
	}
	
	public static class MsgRequest {
		public String msg;
	}
	
	@PostMapping("/send")
	public void sendMsg(@RequestBody MsgRequest msgRequest) {
		sender.sendMsg(msgRequest.msg);
	}
	
	@PostMapping("/send/agazin")
	public void sendMsgToAgazin(@RequestBody MsgRequest msgRequest) {
		sender.sendMsgTo("agazin-exchange",msgRequest.msg);
	}
}
