package shop.geeksasangchat.common.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
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
        System.out.println(msg);

        try {
            ObjectMapper mapper = new ObjectMapper();
            ChattingVO chattingVO = mapper.readValue(msg, ChattingVO.class);

            String exchangeName = "chatting-" + "exchange-" + chattingVO.getChatUUID();

            rabbitTemplate.convertAndSend(exchangeName, "asd", chattingVO.getMsg());
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
     * 소켓 종료
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
