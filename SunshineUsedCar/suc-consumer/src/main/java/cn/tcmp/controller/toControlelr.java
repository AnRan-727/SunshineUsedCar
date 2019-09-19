package cn.tcmp.controller;

import cn.tcmp.dto.TokenDto;
import cn.tcmp.service.CarUserService;
import cn.tcmp.service.TokenService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/9
 */
@Controller
public class toControlelr {
    @Reference
    private TokenService tokenService;
    @Reference
    private CarUserService carUserService;

    @RequestMapping("/")
    public String to(HttpServletRequest request, Model model){
        TokenDto tokenDto = new TokenDto();
        String token = request.getHeader("Cookie");
        if(token != null && token != ""){
            //1.验证Token
            String[] idStr = token.split("-");
            Integer id = Integer.parseInt(idStr[2]);
            String[] tkStr = token.split(";");
            token = tkStr[0].substring(6);
            System.err.println("Token>>>>>>"+token);
            Boolean result=tokenService.checkToken(token,
                    request.getHeader("User-Agent"));
            if(result){
                model.addAttribute("carUserName",carUserService.detailCarUser(id));
            }
        }
        return "qianDuan/index";
    }
    @RequestMapping("login")
    public String login(){
        return "houtai/login";
    }
    @RequestMapping("index")
    public String index(){
        return "houtai/index";
    }
    @RequestMapping("welcome")
    public String welcome(){
        return "houtai/welcome";
    }

}
