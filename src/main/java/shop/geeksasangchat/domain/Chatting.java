package shop.geeksasangchat.domain;

import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Chatting {

    @Id
    public String id;

    public String content;

    public Chatting(String content) {
        this.content = content;
    }
}
