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
public class Domitory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="domitory_id")
    private int id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="university_id")
    private University university;

    private String name;
}