package shop.geeksasang.domain;

import lombok.Getter;

import javax.persistence.*;

import shop.geeksasang.config.domain.BaseEntity;

@Getter
@Entity
public class HashTag extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="hash_tag_id")
    private int id;

    private String title;

    @ManyToMany
    private DeliveryParty deliveryParty;
}