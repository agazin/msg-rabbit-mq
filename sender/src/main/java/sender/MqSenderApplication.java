package sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MqSenderApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(MqSenderApplication.class, args);
	}
}
