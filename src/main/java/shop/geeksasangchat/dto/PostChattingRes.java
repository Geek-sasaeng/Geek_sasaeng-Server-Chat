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

    private String chattingId;

    private String email;

    @NotEmpty
    private String chattingRoomId;

    @NotEmpty
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public PostChattingRes(String chattingRoomId, String content, LocalDateTime createdAt) {
        this.chattingRoomId = chattingRoomId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public PostChattingRes(String email, String chattingRoomId, String content, LocalDateTime createdAt) {
        this.email = email;
        this.chattingRoomId = chattingRoomId;
        this.content = content;
        this.createdAt = createdAt;
    }


}
