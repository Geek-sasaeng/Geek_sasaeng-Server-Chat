package shop.geeksasangchat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shop.geeksasangchat.domain.Chatting;

@Repository
public interface ChattingRepository extends MongoRepository <Chatting, String> {
}
