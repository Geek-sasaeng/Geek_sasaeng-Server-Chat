package shop.geeksasangchat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped;
import shop.geeksasangchat.common.domain.BaseEntity;

@Document //@Document는객체를 몽고DB에 영속화시킴 = SpringDataJpa의 @Entity와 같은 역할
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChattingMember {

    @Id
    private String id;

    private String nickName;
    private String email;
    private String profileImgUrl;

    @Unwrapped(onEmpty = Unwrapped.OnEmpty.USE_EMPTY)
    private BaseEntity baseEntity;
}
