package shop.geeksasangchat.common.exception;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */

    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),
    EXPIRED_JWT(false, 2004,"만료기간이 지난 JWT입니다. 재로그인 바랍니다."),
    NOT_EXISTS_CHATTING_ROOM(false,2005,"채팅방이 존재하지 않습니다."),


    /**
     * 3000 : Response 오류
     */


    /**
     * 4000 : Database, Server 오류
     */

    INTERNAL_SERVER_ERROR(false,4000,"서버 오류입니다."),
    DATABASE_SERVER_ERROR(false,4001,"데이터베이스 서버 오류입니다.");



    // 5000 : 필요시 만들어서 쓰세요


    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
