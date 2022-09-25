package shop.geeksasangchat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

import lombok.NoArgsConstructor;
import shop.geeksasangchat.config.domain.BaseEntity;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class DeliveryPartyRoom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="delivery_party_room_id")
    private int id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member participant;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="delivery_party_id")
    private DeliveryParty party;
}