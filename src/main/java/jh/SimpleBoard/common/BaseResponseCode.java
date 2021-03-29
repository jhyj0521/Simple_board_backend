package jh.SimpleBoard.common;

public enum BaseResponseCode {

    CODE_200, // 정상 처리
    CODE_100, // 필수 변수 값 없음
    CODE_101, // 아이디 중복
    CODE_102, // 아이디 없거나, 비밀번호 불일치
    CODE_103, // 권한 없음
    CODE_401, // 로그인 필요
}
