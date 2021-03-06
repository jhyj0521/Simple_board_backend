package jh.SimpleBoard.common;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil {

    /**
     * request에 저장한 memberNo 값을 반환
     * @param request
     * @return
     */
    public static long getMemberNo(HttpServletRequest request) {
        String memberPk = (String) (request.getAttribute("memberPk"));
        return Long.parseLong(memberPk);
    }

    /**
     * request에 저장한 memberName 값을 반환
     * @param request
     * @return
     */
    public static String getMemberName(HttpServletRequest request) {
        String memberName = (String) (request.getAttribute("memberName"));
        return memberName;
    }
}
