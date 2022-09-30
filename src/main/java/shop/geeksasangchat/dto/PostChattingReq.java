package shop.geeksasangchat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public
class PostChattingReq {

    private String chattingRoomId;
    private String content;

    public PostChattingReq(String chattingRoomId, String content) {
        this.chattingRoomId = chattingRoomId;
        this.content = content;
    }
}
