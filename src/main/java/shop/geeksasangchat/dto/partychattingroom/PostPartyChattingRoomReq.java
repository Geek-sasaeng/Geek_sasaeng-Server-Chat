package shop.geeksasangchat.dto.partychattingroom;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class PostPartyChattingRoomReq {
    @NotBlank
    private String title;
}
