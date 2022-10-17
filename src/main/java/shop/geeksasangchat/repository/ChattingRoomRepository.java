package shop.geeksasangchat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import shop.geeksasangchat.domain.ChattingRoom;

import java.util.List;

@Repository
public interface ChattingRoomRepository extends MongoRepository<ChattingRoom, String> {

    @Query("{id:'?0'}") // 0번째 파라미터 조건
    List<ChattingRoom> findAllByPartyChattingRoomId(String id);
}
