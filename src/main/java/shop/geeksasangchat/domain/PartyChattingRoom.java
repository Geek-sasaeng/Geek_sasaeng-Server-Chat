package shop.geeksasangchat.domain;

public class PartyChattingRoom extends ChattingRoom{

    private String title;

    public PartyChattingRoom(String title) {
        super();
        this.title = title;
    }

    @Override
    public String toString() {
        return "DeliveryPartyChattingRoom{" +
                "id='" + id + '\'' +
                ", baseEntity=" + baseEntity +
                ", title='" + title + '\'' +
                '}';
    }



}
