package jh.SimpleBoard.controller;

import jh.SimpleBoard.common.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static jh.SimpleBoard.common.CommonUtil.getMemberName;
import static jh.SimpleBoard.common.CommonUtil.getMemberNo;

@RestController
@RequestMapping("/jwt")
public class JwtController {
    @PostMapping()
    public BaseResponse getJwtInfo(HttpServletRequest request) {
        long memberNo = getMemberNo(request);
        String memberName = getMemberName(request);

        Map<String, Object> data = new HashMap<>();
        data.put("memberNo", memberNo);
        data.put("memberName", memberName);

        return new BaseResponse(data);
    }
}
