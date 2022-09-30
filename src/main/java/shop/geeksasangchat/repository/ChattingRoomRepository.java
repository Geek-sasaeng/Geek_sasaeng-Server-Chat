package shop.geeksasangchat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shop.geeksasangchat.domain.ChattingRoom;

@Repository
public interface ChattingRoomRepository extends MongoRepository<ChattingRoom, String> {
}
