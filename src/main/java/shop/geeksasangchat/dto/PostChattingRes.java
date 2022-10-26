package shop.geeksasangchat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class PostChattingRes {

    @NotEmpty
    private String chattingRoomId;

    @NotEmpty
    private String content;

    private LocalDateTime createdAt;

    public PostChattingRes(String chattingRoomId, String content, LocalDateTime createdAt) {
        this.chattingRoomId = chattingRoomId;
        this.content = content;
        this.createdAt = createdAt;
    }
}
