package shop.geeksasangchat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import shop.geeksasangchat.dto.PostChattingRes;
import shop.geeksasangchat.rabbitmq.MQController;
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
    private final MQController mqController;

    @Transactional(readOnly = false)
    public String createChattingRoom(int userId, String title){
        List<Chatting> chattings = new ArrayList<>();
        List<ParticipantInfo> participants = new ArrayList<>();
        PartyChattingRoom chattingRoom = new PartyChattingRoom(title, chattings, participants, "123", "국민", "Delivery", false, 5);
        PartyChattingRoom saveChattingRoom = partyChattingRoomRepository.save(chattingRoom);
        return saveChattingRoom.getId();
    }

    @Transactional(readOnly = false)
    public void createChatting(int userId, String email, String chattingRoomId, String content) {
        // mongoDB 채팅 저장
        Chatting chatting = new Chatting(content);
        Chatting saveChatting = chattingRepository.save(chatting);
//        partyChattingQueue.send(saveChatting, chattingRoomId); // 저장한 채팅 rabbitmq를 이용해 Consumer에게 메시지 전송

        // json 형식으로 변환 후 RabbitMQ 전송
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        PostChattingRes postChattingRes = new PostChattingRes(email, chattingRoomId, saveChatting.getContent(), saveChatting.getBaseEntity().getCreatedAt()); // ObjectMapper가 java8의 LocalDateTime을 지원하지 않는 에러 해결
        String saveChattingJsonStr = null;
        try {
            saveChattingJsonStr = mapper.writeValueAsString(postChattingRes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        mqController.sendMessage(saveChattingJsonStr, chattingRoomId); // rabbitMQ 메시지 publish
    }

    @Transactional(readOnly = false)
    public void joinPartyChattingRoom(String chattingRoomId, LocalDateTime enterTime, boolean isRemittance, Long memberId){

        PartyChattingRoom partyChattingRoom = partyChattingRoomRepository.findByPartyChattingRoomId(chattingRoomId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_EXISTS_CHATTING_ROOM));

        // 파티 입장하는 멤버 정보 추가
        ParticipantInfo participantInfo = new ParticipantInfo(LocalDateTime.now(), isRemittance, memberId);

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