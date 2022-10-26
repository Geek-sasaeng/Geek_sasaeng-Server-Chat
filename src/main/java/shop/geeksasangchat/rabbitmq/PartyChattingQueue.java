package shop.geeksasangchat.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import shop.geeksasangchat.domain.Chatting;
import shop.geeksasangchat.dto.PostChattingRes;

public class PartyChattingQueue {

    private Queue queue;
    private TopicExchange topicExchange;
    private RabbitTemplate rabbitTemplate;

    static final String EXCHANGE_NAME = "chatting-room-exchange-test2";
    static final String QUEUE_NAME = "chatting-room-queue-test2";
    static final String ROUTING_KEY = "chatting.test.room.#"; // 라우팅 키, publishing 하는 방법을 결정.

    public PartyChattingQueue(RabbitTemplate rabbitTemplate ) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = new Queue(QUEUE_NAME, false); // 큐 생성
        this.topicExchange = new TopicExchange(EXCHANGE_NAME); // exchange 생성
    }

    public Queue getQueue() {
        return queue;
    }

    public TopicExchange getTopicExchange() {
        return topicExchange;
    }

    // publish 메소드: 큐애 메시지 보내기
    public void send(Chatting saveChatting, String chattingRoomId) {
        System.out.println("====================" + chattingRoomId);
        System.out.println("chattingRoomId = " + chattingRoomId);
        System.out.println("saveChatting = " + saveChatting);
        // json 형식으로 변환 후 전송
        ObjectMapper mapper = new ObjectMapper();
        PostChattingRes postChattingRes = new PostChattingRes(saveChatting.getId(), saveChatting.getContent(), saveChatting.getBaseEntity().getCreatedAt());
        String saveChattingJsonStr = null;
        try {
            saveChattingJsonStr = mapper.writeValueAsString(postChattingRes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, chattingRoomId, saveChattingJsonStr); // convertAndSend(exchange, 라우팅 키, 메시지 내용) : EXCHANGE를 통해 라우팅 키에 해당하는 큐에 메시지 전송.
    }
}

/**
 * * (star) can substitute for exactly one word.
 * # (hash) can substitute for zero or more words.
 */