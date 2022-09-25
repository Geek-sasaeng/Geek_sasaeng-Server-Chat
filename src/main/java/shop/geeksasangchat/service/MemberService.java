package shop.geeksasangchat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.geeksasangchat.domain.Member;
import shop.geeksasangchat.dto.CreateMemberReq;
import shop.geeksasangchat.repository.MemberRepository;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = false)
    public Member createMember(CreateMemberReq dto){

        /**
         *
         *  if(dto.getCheckPassword() != dto.getPassword()){
         *             throw new RuntimeException();
         *         }
         */

        //ToDo 로그인 아이디 중복

        //ToDo email 중복

        Member member = dto.toEntity();
        memberRepository.save(member);


        return member;
    }
}
