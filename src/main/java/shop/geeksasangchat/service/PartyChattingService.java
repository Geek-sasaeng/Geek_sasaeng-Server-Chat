package shop.geeksasangchat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.geeksasangchat.domain.Chatting;
import shop.geeksasangchat.domain.ChattingRoom;
import shop.geeksasangchat.domain.PartyChattingRoom;
import shop.geeksasangchat.rabbitmq.PartyChattingQueue;
import shop.geeksasangchat.repository.ChattingRepository;
import shop.geeksasangchat.repository.ChattingRoomRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartyChattingService {

    private final ChattingRoomRepository chattingRoomRepository;
    private final ChattingRepository chattingRepository;
    private final PartyChattingQueue partyChattingQueue;

    @Transactional(readOnly = false)
    public String createChattingRoom(int userId, String title){
        PartyChattingRoom chattingRoom = new PartyChattingRoom(title);
        PartyChattingRoom saveChattingRoom = chattingRoomRepository.save(chattingRoom);
        return saveChattingRoom.getId();
    }

    @Transactional(readOnly = false)
    public void createChatting(int userId, String chattingRoomId, String content, int participantsCnt) {
        Chatting chatting = new Chatting(content);
        Chatting saveChatting = chattingRepository.save(chatting);
        partyChattingQueue.send(saveChatting, chattingRoomId, participantsCnt); // 저장한 채팅 rabbitmq를 이용해 Consumer에게 메시지 전송

    }

    @Transactional(readOnly = true)
    public List<ChattingRoom> findAllPartyChattingRooms(int userId){
//        List<String> partyChattingRoomList = chattingRoomRepository.findAll().stream()
//                .map(chattingRoom -> chattingRoom.getId())
//                .collect(Collectors.toList());
        List<ChattingRoom> partyChattingRoomList = chattingRoomRepository.findAll();

        return partyChattingRoomList;
    }

    @Transactional(readOnly = true)
    public List<ChattingRoom> findPartyChattingRoom(int userId, String partyChattingRoomId){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("id").is(partyChattingRoomId));
        List<ChattingRoom> partyChattingRoomList = chattingRoomRepository.findAllByPartyChattingRoomId(partyChattingRoomId);

        return partyChattingRoomList;
    }

    @Transactional(readOnly = true)
    public List<Chatting> findPartyChattings(int userId, String partyChattingRoomId){
        List<Chatting> chattingList = chattingRepository.findAll();


        return chattingList;
    }


}
   // String exchange, String routingKey, Object message