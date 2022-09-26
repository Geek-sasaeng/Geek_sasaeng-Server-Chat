package shop.geeksasangchat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.geeksasangchat.domain.Chatting;

public interface ChattingRepository extends MongoRepository <Chatting, String> {
}
