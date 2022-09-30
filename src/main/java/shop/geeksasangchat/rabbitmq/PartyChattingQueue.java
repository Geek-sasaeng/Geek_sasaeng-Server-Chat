package shop.geeksasangchat.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import shop.geeksasangchat.domain.Chatting;

public class PartyChattingQueue {

    private Queue queue;
    private TopicExchange topicExchange;
    private RabbitTemplate rabbitTemplate;

    static final String EXCHANGE_NAME = "chatting-room-exchange";
    static final String QUEUE_NAME = "chatting-room-queue";
    static final String ROUTING_KEY = "chatting.room.#";

    public PartyChattingQueue(RabbitTemplate rabbitTemplate ) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = new Queue(QUEUE_NAME, false);;
        this.topicExchange = new TopicExchange(EXCHANGE_NAME);;
    }

    public Queue getQueue() {
        return queue;
    }

    public TopicExchange getTopicExchange() {
        return topicExchange;
    }

    public void send(Chatting saveChatting, String chattingRoomId) {
        System.out.println("chattingRoomId = " + chattingRoomId);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, chattingRoomId, saveChatting);
    }
}

/**
 * * (star) can substitute for exactly one word.
 * # (hash) can substitute for zero or more words.
 */