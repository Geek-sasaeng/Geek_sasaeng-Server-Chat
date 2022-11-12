package shop.geeksasangchat.rabbitmq;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.geeksasangchat.utils.jwt.NoIntercept;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rabbitmq")
public class MQController {
    private final AmqpAdmin admin;

    private final RabbitTemplate rabbitTemplate;

    private final String EXCHANGE = "chatting-room-exchange-test2";

    @GetMapping("/create")
    @NoIntercept
    public String createChattingRoom(@RequestParam String email, @RequestParam String chatUUID){

        String exchangeName = "chatting-" + "exchange-" + chatUUID;
        Exchange exchange = new FanoutExchange(exchangeName);
        admin.declareExchange(exchange);

        Queue queue = new Queue(email, true, false, false);

        QueueInformation queueInfo = admin.getQueueInfo("email");
        // 큐가 없으면 생성
        if(queueInfo == null){
            admin.declareQueue(queue);
        }
        Binding binding = new Binding(email, Binding.DestinationType.QUEUE, exchangeName, "asdf",null);

        admin.declareQueue(queue);
        admin.declareBinding(binding);

        return "OK";
    }

    @GetMapping
    @NoIntercept
    public String joinChattingRoom(@RequestParam String email, @RequestParam String chatUUID){
        String exchangeName = "chatting-" + "exchange-" + chatUUID;

        Queue queue = new Queue(email, true, false, false);

        QueueInformation queueInfo = admin.getQueueInfo("email");
        // 큐가 없으면 생성
        if(queueInfo == null){
            admin.declareQueue(queue);
        }
        Binding binding = new Binding(email, Binding.DestinationType.QUEUE, exchangeName, "asdf",null);

        admin.declareQueue(queue);
        admin.declareBinding(binding);
        return "OK";
    }

    @GetMapping("/msg")
    @NoIntercept
    public String sendMessage(@RequestParam String msg, @RequestParam String chatUUID){
        String exchangeName = "chatting-" + "exchange-" + chatUUID;

        rabbitTemplate.convertAndSend(exchangeName, "asdf", msg);
        return "OK";
    }

    public String getRoutingKey(String queueName){
        String[] routingKey = queueName.split("\\.");


        StringBuilder sb = new StringBuilder();

        sb.append(routingKey[0]);
        sb.append(".");
        sb.append(routingKey[1]);
        sb.append(".*");

        return sb.toString();
    }
}
