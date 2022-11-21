package shop.geeksasangchat.service;

import com.rabbitmq.client.AMQP;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.geeksasangchat.common.exception.BaseException;
import shop.geeksasangchat.common.exception.BaseResponseStatus;
import shop.geeksasangchat.domain.*;
import shop.geeksasangchat.rabbitmq.PartyChattingQueue;
import shop.geeksasangchat.repository.ChattingRepository;
import shop.geeksasangchat.repository.ChattingRoomRepository;
import shop.geeksasangchat.repository.PartyChattingRoomRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartyChattingService {

    private final ChattingRoomRepository chattingRoomRepository;
    private final ChattingRepository chattingRepository;
    private final PartyChattingQueue partyChattingQueue;
    private final PartyChattingRoomRepository partyChattingRoomRepository;

    @Transactional(readOnly = false)
    public String createChattingRoom(int userId, String title){
        List<Chatting> chattings = new ArrayList<>();
        List<ParticipantInfo> participants = new ArrayList<>();
        PartyChattingRoom chattingRoom = new PartyChattingRoom(title, chattings, participants);
        PartyChattingRoom saveChattingRoom = partyChattingRoomRepository.save(chattingRoom);
        return saveChattingRoom.getId();
    }

    @Transactional(readOnly = false)
    public void createChatting(int userId, String chattingRoomId, String content, int participantsCnt) {
        Chatting chatting = new Chatting(content);
        Chatting saveChatting = chattingRepository.save(chatting);
        partyChattingQueue.send(saveChatting, chattingRoomId, participantsCnt); // 저장한 채팅 rabbitmq를 이용해 Consumer에게 메시지 전송

    }

    @Transactional(readOnly = false)
    public void joinPartyChattingRoom(String chattingRoomId, LocalDateTime enterTime, boolean isRemittance, String nickName){

        PartyChattingRoom partyChattingRoom = partyChattingRoomRepository.findByPartyChattingRoomId(chattingRoomId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_EXISTS_CHATTING_ROOM));

        // 파티 입장하는 멤버 정보 추가
        ParticipantInfo participantInfo = new ParticipantInfo(LocalDateTime.now(), isRemittance, nickName);
        partyChattingRoom.changeParticipants(participantInfo);
        partyChattingRoomRepository.save(partyChattingRoom); // MongoDB는 JPA처럼 변경감지가 안되어서 직접 저장해줘야 한다.
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