package shop.geeksasangchat.rabbitmq;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChattingVO {
    private String chatUUID;
    private String msg;
}
