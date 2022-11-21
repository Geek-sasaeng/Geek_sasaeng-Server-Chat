package shop.geeksasangchat.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@NoArgsConstructor
public class PartyChattingRoom extends ChattingRoom{

    private String title;

    //TODO:채팅과 일대다 연관관계 테스트 중
//    @DBRef
//    private List<Chatting> chattings;
    @DocumentReference // 일대다
    private List<Chatting> chattings = new ArrayList<>();

//    @DocumentReference // 일대다
    private List<ParticipantInfo> participants = new ArrayList<>();

//    private String accountNumber;
//    private String bank;
//    private String category;
//    private boolean isFinish;
//    private Integer maxMatching;

    public PartyChattingRoom(String title) {
        super();
        this.title = title;
    }

    public PartyChattingRoom(String title, List<Chatting> chattings) {
        super();
        this.title = title;
        this.chattings = chattings;
    }

    public PartyChattingRoom(String title, List<Chatting> chattings, List<ParticipantInfo> participants) {
        this.title = title;
        this.chattings = chattings;
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "DeliveryPartyChattingRoom{" +
                "id='" + id + '\'' +
                ", baseEntity=" + baseEntity +
                ", title='" + title + '\'' +
                '}';
    }

    public void changeParticipants(ParticipantInfo participantInfo){
        this.participants.add(participantInfo);
    }



}
