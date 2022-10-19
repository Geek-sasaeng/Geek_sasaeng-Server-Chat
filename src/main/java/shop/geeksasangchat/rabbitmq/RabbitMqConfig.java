package shop.geeksasangchat.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {

    private final RabbitTemplate rabbitTemplate; // rabbitMQ 라이브러리 클래스

    @Bean// 큐 생성 빈
    public PartyChattingQueue partyChattingQueueImpl(){
        return new PartyChattingQueue(rabbitTemplate);
    }

    @Bean // 큐, exchange 바인딩 빈
    Binding partyChattingQueueBinding(PartyChattingQueue queue) {
        return BindingBuilder.bind(queue.getQueue()).to(queue.getTopicExchange()).with(PartyChattingQueue.ROUTING_KEY); // bind(바인딩할 큐, exchange, 라우팅 키)
    }

//    @Bean
//    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(PartyChattingQueue.QUEUE_NAME);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }
//
//    @Bean
//    MessageListenerAdapter listenerAdapter(Receiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }


//    // 5번 튜토리얼 Consumer 빈 등록
//    @Bean
//    public Tut5Receiver receiver() {
//        return new Tut5Receiver();
//    }
}
