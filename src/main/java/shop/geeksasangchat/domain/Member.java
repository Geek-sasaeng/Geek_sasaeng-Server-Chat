package shop.geeksasangchat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.geeksasangchat.config.domain.BaseEntity;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private int id;

    private String loginId;

    private String nickName;

    private String email;

    private String password;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="university_id")
    private University university;

    private String phoneNumber;

    private String phoneValidKey;

    private String profileImgUrl;

    private String emailValidKey;

    private String emailValidStatus;

    private String jwtToken;

    public Member(String loginId, String nickName, String email, String password) {
        this.loginId = loginId;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
    }
}