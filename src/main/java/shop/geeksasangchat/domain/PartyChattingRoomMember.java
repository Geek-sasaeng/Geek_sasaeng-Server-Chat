package shop.geeksasangchat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document //@Document는객체를 몽고DB에 영속화시킴 = SpringDataJpa의 @Entity와 같은 역할
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PartyChattingRoomMember {

    @Id
    public String id;

    @DocumentReference(lazy = true) // 다대일
    private PartyChattingRoom partyChattingRoom;

}
