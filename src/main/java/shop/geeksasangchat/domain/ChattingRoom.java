package shop.geeksasangchat.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped;
import shop.geeksasangchat.common.domain.BaseEntity;

//@Document  //추상 클래스로 때려 박을 수 있음. 신기
public abstract class ChattingRoom { // 배달, 중고거래, 커뮤니티 등 서로다른 분류의 채팅방 공통 부분 추상 클래스

    @Id
    protected String id;

    @Unwrapped(onEmpty = Unwrapped.OnEmpty.USE_EMPTY)
    protected BaseEntity baseEntity;

    public ChattingRoom() {
        this.baseEntity = new BaseEntity();
    }

    public String getId() {
        return id;
    }
}
