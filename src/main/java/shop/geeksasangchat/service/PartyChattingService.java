package shop.geeksasangchat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import shop.geeksasangchat.domain.Chatting;
import shop.geeksasangchat.domain.PartyChattingRoom;
import shop.geeksasangchat.rabbitmq.PartyChattingQueue;
import shop.geeksasangchat.repository.ChattingRepository;
import shop.geeksasangchat.repository.ChattingRoomRepository;

@Service
@RequiredArgsConstructor
public class PartyChattingService {

    private final ChattingRoomRepository chattingRoomRepository;
    private final ChattingRepository chattingRepository;
    private final PartyChattingQueue partyChattingQueue;

    public String createChattingRoom(int userId, String title){
        PartyChattingRoom chattingRoom = new PartyChattingRoom(title);
        PartyChattingRoom saveChattingRoom = chattingRoomRepository.save(chattingRoom);
        return saveChattingRoom.getId();
    }

    public void createChatting(int userId, String chattingRoomId, String content) {
        Chatting chatting = new Chatting("content");
        Chatting saveChatting = chattingRepository.save(chatting);
        partyChattingQueue.send(saveChatting, chattingRoomId);

    }
}
   // String exchange, String routingKey, Object message