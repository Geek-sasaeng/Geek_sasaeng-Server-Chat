package shop.geeksasangchat.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document //@Document는객체를 몽고DB에 영속화시킴 = SpringDataJpa의 @Entity와 같은 역할
@ToString
@Getter
@NoArgsConstructor
public class ParticipantInfo {
    private LocalDateTime enterTime;
    private boolean isRemittance;
    private String nickName;

    public ParticipantInfo(LocalDateTime enterTime, boolean isRemittance, String nickName) {
        this.enterTime = enterTime;
        this.isRemittance = isRemittance;
        this.nickName = nickName;
    }
}
