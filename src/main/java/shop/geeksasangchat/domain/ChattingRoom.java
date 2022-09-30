package shop.geeksasangchat.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped;
import shop.geeksasangchat.common.domain.BaseEntity;

@Document  //추상 클래스로 때려 박을 수 있음. 신기
public abstract class ChattingRoom {

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
