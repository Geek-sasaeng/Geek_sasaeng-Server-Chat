package shop.geeksasangchat.domain;

import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped;
import shop.geeksasangchat.common.domain.BaseEntity;

import java.io.Serializable;

@Document
@ToString
public class Chatting  implements Serializable {

    @Id
    public String id;

    public String content;

    @Unwrapped(onEmpty = Unwrapped.OnEmpty.USE_EMPTY)
    private BaseEntity baseEntity;

    public Chatting(String content) {
        this.content = content;
        this.baseEntity = new BaseEntity();
    }
}

/**
 * @Unwrapped
 * 값 개체를 대상 문서에서 평평하게 구성하는 주석
 * 결과 집합에서 읽을 때 랩되지 않은 모든 값이 null인 경우 onEmpty() 값에 따라 속성이 null 또는 빈 인스턴스로 설정
 *
 */