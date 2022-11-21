package shop.geeksasangchat.common.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import shop.geeksasangchat.dto.PostChattingRes;
import shop.geeksasangchat.rabbitmq.ChattingVO;

import java.util.HashMap;

@RequiredArgsConstructor
@Component
public class SocketHandler extends TextWebSocketHandler {
    private final RabbitTemplate rabbitTemplate;
    private final String EXCHANGE = "chatting-room-exchange-test2";

    HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵

    /**
    * 메시지 발송
    * @author 토마스, 네오
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String msg = message.getPayload();
        System.out.println("전송하는 웹소켓 메시지="+msg);

        try {
//            ObjectMapper mapper = new ObjectMapper();
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            ChattingVO chattingVO = mapper.readValue(msg, ChattingVO.class);

            String exchangeName = "chatting-" + "exchange-" + chattingVO.getChattingRoomUUID();

            // json 형식으로 변환 후 전송
//            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
//            PostChattingRes postChattingRes = new PostChattingRes(chattingRoomId, saveChatting.getContent(), saveChatting.getBaseEntity().getCreatedAt()); // ObjectMapper가 java8의 LocalDateTime을 지원하지 않는 에러 해결
//            PostChattingRes postChattingRes = mapper.readValue(msg, PostChattingRes.class);
            String saveChattingJsonStr = null;
            try {
                saveChattingJsonStr = mapper.writeValueAsString(chattingVO);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
//            for(int i=0;i<participantsCnt;i++){
//                rabbitTemplate.convertAndSend(EXCHANGE_NAME, chattingRoomId, saveChattingJsonStr); // convertAndSend(exchange, 라우팅 키, 메시지 내용) : EXCHANGE를 통해 라우팅 키에 해당하는 큐에 메시지 전송.
////            rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_NAME, chattingRoomId, saveChattingJsonStr);
//            }

            rabbitTemplate.convertAndSend(exchangeName, "asd", saveChattingJsonStr);

        } catch (Exception e) {

        }

    }

    /**
     * 소켓 연결
     * @author 토마스, 네오
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        super.afterConnectionEstablished(session);
        sessionMap.put(session.getId(), session);
        System.out.println("connect");
    }

    /**
     * 소켓 종료
     * @author 토마스, 네오
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        sessionMap.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }

    /**
     *
     * @author 토마스, 네오
     */
    public String getRoutingKey(String queueName) {
        String[] routingKey = queueName.split("\\.");

        StringBuilder sb = new StringBuilder();

        sb.append(routingKey[0]);
        sb.append(".");
        sb.append(routingKey[1]);
        sb.append(".*");
        return sb.toString();
    }
}
