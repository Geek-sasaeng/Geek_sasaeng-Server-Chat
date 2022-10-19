package shop.geeksasangchat.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class PartyChattingRoom extends ChattingRoom{

    private String title;

    //TODO: 채팅과 일대다 연관관계 테스트 중
//    @DBRef
//    private List<Chatting> chattings;

    public PartyChattingRoom(String title) {
        super();
        this.title = title;
    }

    @Override
    public String toString() {
        return "DeliveryPartyChattingRoom{" +
                "id='" + id + '\'' +
                ", baseEntity=" + baseEntity +
                ", title='" + title + '\'' +
                '}';
    }



}
