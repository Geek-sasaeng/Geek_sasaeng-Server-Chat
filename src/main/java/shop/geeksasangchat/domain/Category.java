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
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private int id;

    private String title;
}