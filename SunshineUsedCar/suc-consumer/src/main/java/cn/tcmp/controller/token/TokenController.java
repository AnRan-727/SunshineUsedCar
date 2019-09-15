package cn.tcmp.controller.token;

import cn.tcmp.dto.TokenDto;
import cn.tcmp.service.TokenService;
import cn.tcmp.util.Common;
import com.alibaba.fastjson.JSON;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/8/28/028.
 */
@RestController
@RequestMapping("token")
public class TokenController {

    @Reference
    private TokenService tokenService;

    @RequestMapping(value = "reset",method = RequestMethod.POST,
    produces = {"application/json;charset=UTF-8"})
    public String reset(HttpServletRequest request) {
        //从header中获取token和userAgent
        String token=request.getHeader("token");
        String userAgent = request.getHeader("User-Agent");
        //调用业务层方法token重置
        TokenDto tokenDto = tokenService.reset(token, userAgent);
        //返回
        return JSON.toJSONString(tokenDto);
    }

    @RequestMapping(value = "check",method = RequestMethod.POST,
            produces ={"application/json;charset=UTF-8"} )
    public String check(HttpServletRequest request) {
        //获取userAgent和token
        String agent = request.getHeader("User-Agent");
        String token=request.getHeader("token");
        Boolean result = tokenService.checkToken(token, agent);
        TokenDto tokenDto=new TokenDto();
        if (result) {
            tokenDto.setErrorcode(Common.CODE_SUCCESS);
            return JSON.toJSONString(tokenDto);
        }
        tokenDto.setErrorcode(Common.CODE_03);
        return JSON.toJSONString(tokenDto);
    }
}
