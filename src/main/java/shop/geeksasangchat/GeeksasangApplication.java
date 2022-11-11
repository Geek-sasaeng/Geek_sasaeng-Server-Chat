package shop.geeksasangchat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing //createdDate와 updatedDate을 사용하기 위해서 해당 애노테이션을 붙임.
@SpringBootApplication
public class GeeksasangApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeeksasangApplication.class, args);
	}

}

//public class GeeksasangApplication implements CommandLineRunner {
//
//
//	@Autowired
//	private RabbitmqProducer rabbitmqProducer;
//
//	public static void main(String[] args) {
//		SpringApplication.run(GeeksasangApplication.class, args);
//	}
//
//	@Override
//	public void run(String... args) {
//
//		rabbitmqProducer.sendHello("helloTest1");
//	}
//}
