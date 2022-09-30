package shop.geeksasangchat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.geeksasangchat.domain.ChattingRoom;
import shop.geeksasangchat.domain.PartyChattingRoom;
import shop.geeksasangchat.repository.ChattingRepository;
import shop.geeksasangchat.repository.ChattingRoomRepository;

import java.util.List;

@SpringBootTest
public class Teest {

    @Autowired
    private ChattingRepository repository;

    @Autowired
    private ChattingRoomRepository chattingRoomRepository;

    @Test
    void name() {
        PartyChattingRoom deliveryPartyChattingRoom = new PartyChattingRoom("title");
        PartyChattingRoom save = chattingRoomRepository.save(deliveryPartyChattingRoom);
        System.out.println("save = " + save.toString());

        List<ChattingRoom> all = chattingRoomRepository.findAll();

        System.out.println("all = " + all);
        chattingRoomRepository.deleteAll();
    }

    @Test
    void name3123() {

        List<ChattingRoom> all = chattingRoomRepository.findAll();
        System.out.println("all.size() = " + all.size());
        for (ChattingRoom chattingRoom : all) {
            System.out.println("chattingRoom = " + chattingRoom);
        }
    }

    @Test
    void remove() {
        repository.deleteAll();
    }
}
