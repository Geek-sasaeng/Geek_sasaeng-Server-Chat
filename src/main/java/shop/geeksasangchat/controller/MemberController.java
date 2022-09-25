package shop.geeksasangchat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.geeksasangchat.config.response.BaseResponse;
import shop.geeksasangchat.domain.Member;
import shop.geeksasangchat.dto.CreateMemberReq;
import shop.geeksasangchat.dto.CreateMemberRes;
import shop.geeksasangchat.service.MemberService;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public BaseResponse<CreateMemberRes> createMember(@RequestBody CreateMemberReq dto){
        Member member = memberService.createMember(dto);

        CreateMemberRes createMemberRes = CreateMemberRes.toDto(member);
        return new BaseResponse<>(createMemberRes);
    }

}
