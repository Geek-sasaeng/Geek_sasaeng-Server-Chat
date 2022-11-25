package shop.geeksasangchat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import shop.geeksasangchat.domain.ChattingRoom;
import shop.geeksasangchat.domain.PartyChattingRoom;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartyChattingRoomRepository extends MongoRepository<PartyChattingRoom, String> {
    @Query("{id:'?0'}") // 0번째 파라미터 조건
    Optional<PartyChattingRoom> findByPartyChattingRoomId(String id);
}
