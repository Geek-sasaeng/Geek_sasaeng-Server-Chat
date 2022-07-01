package shop.geeksasang.domain;

import lombok.Getter;

import javax.persistence.*;

import shop.geeksasang.config.domain.BaseEntity;
import shop.geeksasang.domain.DeliveryParty;
import shop.geeksasang.domain.Member;

@Getter
@Entity
public class DeliveryPartyRoom extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="delivery_party_room_id")
    private int id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member participant;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="delivery_party_id")
    private DeliveryParty party;
}