package shop.geeksasangchat.dto.chattingmember;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class PostParticipantInfoReq {
    @NotEmpty
    private String chattingRoomId;

    private Boolean isRemittance;
    @NotEmpty
    private String nickName;
}
