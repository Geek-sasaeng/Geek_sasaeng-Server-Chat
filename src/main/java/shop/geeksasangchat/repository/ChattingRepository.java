package shop.geeksasangchat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import shop.geeksasangchat.domain.Chatting;
import shop.geeksasangchat.domain.ChattingRoom;

import java.util.List;

@Repository
public interface ChattingRepository extends MongoRepository <Chatting, String> {
}
