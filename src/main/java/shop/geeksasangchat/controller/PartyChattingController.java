package shop.geeksasangchat.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.geeksasangchat.common.response.BaseResponse;
import shop.geeksasangchat.domain.Chatting;
import shop.geeksasangchat.domain.ChattingRoom;
import shop.geeksasangchat.domain.PartyChattingRoom;
import shop.geeksasangchat.dto.PostChattingReq;
import shop.geeksasangchat.service.PartyChattingService;
import shop.geeksasangchat.utils.jwt.JwtInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        return new BaseResponse("채팅송신을 성공했습니다.");
    }


    @ApiOperation(value = "채팅방 전체 조회", notes = "(jwt 토큰 필요)전체 조회")
    @ApiResponses({
            @ApiResponse(code = 1000 ,message ="요청에 성공하셨습니다."),
            @ApiResponse(code = 2009, message ="존재하지 않는 멤버입니다"),
            @ApiResponse(code = 4000 ,message ="서버 오류입니다.")
    })
    @PostMapping("/get")
    public BaseResponse<List<ChattingRoom>> findAllPartyChattingRooms(HttpServletRequest request){
        JwtInfo jwtInfo = (JwtInfo) request.getAttribute("jwtInfo");
        return new BaseResponse(partyChattingService.findAllPartyChattingRooms(jwtInfo.getUserId()));
    }

    @ApiOperation(value = "채팅방 조회", notes = "(jwt 토큰 필요)전체 조회")
    @ApiResponses({
            @ApiResponse(code = 1000 ,message ="요청에 성공하셨습니다."),
            @ApiResponse(code = 2009, message ="존재하지 않는 멤버입니다"),
            @ApiResponse(code = 4000 ,message ="서버 오류입니다.")
    })
    @PostMapping("/get/{partyChattingRoomId}")
    public BaseResponse<List<ChattingRoom>> findPartyChattingRoom(HttpServletRequest request, @PathVariable("partyChattingRoomId") String partyChattingRoomId){
        JwtInfo jwtInfo = (JwtInfo) request.getAttribute("jwtInfo");
        return new BaseResponse(partyChattingService.findPartyChattingRoom(jwtInfo.getUserId(),partyChattingRoomId));
    }

    @ApiOperation(value = "채팅 전체 조회", notes = "(jwt 토큰 필요)전체 조회")
    @ApiResponses({
            @ApiResponse(code = 1000 ,message ="요청에 성공하셨습니다."),
            @ApiResponse(code = 2009, message ="존재하지 않는 멤버입니다"),
            @ApiResponse(code = 4000 ,message ="서버 오류입니다.")
    })
    @PostMapping("/chatting/get/{partyChattingRoomId}")
    public BaseResponse<List<Chatting>> findPartyChattings(HttpServletRequest request, @PathVariable("partyChattingRoomId") String partyChattingRoomId){
        JwtInfo jwtInfo = (JwtInfo) request.getAttribute("jwtInfo");
        return new BaseResponse(partyChattingService.findPartyChattings(jwtInfo.getUserId(), partyChattingRoomId));
    }
}
