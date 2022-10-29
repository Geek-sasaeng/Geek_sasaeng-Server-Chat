package shop.geeksasangchat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp createdAt;

    public PostChattingRes(String chattingRoomId, String content, Timestamp createdAt) {
        this.chattingRoomId = chattingRoomId;
        this.content = content;
        this.createdAt = createdAt;
    }
}
