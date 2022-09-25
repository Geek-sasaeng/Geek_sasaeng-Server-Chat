package shop.geeksasangchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.geeksasangchat.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
}
