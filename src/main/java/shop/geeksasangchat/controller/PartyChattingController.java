package shop.geeksasangchat.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.geeksasangchat.common.response.BaseResponse;
import shop.geeksasangchat.dto.PostChattingReq;
import shop.geeksasangchat.service.PartyChattingService;
import shop.geeksasangchat.utils.jwt.JwtInfo;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/party-chatting-room")
@RequiredArgsConstructor
public class PartyChattingController {

    private final PartyChattingService partyChattingService;

    @ApiOperation(value = "채팅방 생성", notes = "(jwt 토큰 필요)마이페이지-공지사항에서 공지사항을 전체 조회")
    @ApiResponses({
            @ApiResponse(code = 1000 ,message ="요청에 성공하셨습니다."),
            @ApiResponse(code = 2009, message ="존재하지 않는 멤버입니다"),
            @ApiResponse(code = 4000 ,message ="서버 오류입니다.")
    })
    @PostMapping
    public BaseResponse<Long> createPartyChattingRoom(HttpServletRequest request){
        JwtInfo jwtInfo = (JwtInfo) request.getAttribute("jwtInfo");
        String id = partyChattingService.createChattingRoom(jwtInfo.getUserId(), "title");
        return new BaseResponse(id);
    }

    @PostMapping("/chatting")
    public BaseResponse<String> createPartyChatting(HttpServletRequest request, @RequestBody PostChattingReq dto){
        JwtInfo jwtInfo = (JwtInfo) request.getAttribute("jwtInfo");
        System.out.println("dto.getChattingRoomId() = " + dto.getChattingRoomId());
        partyChattingService.createChatting(jwtInfo.getUserId(), dto.getChattingRoomId(), dto.getContent());
        return null;
    }
}
