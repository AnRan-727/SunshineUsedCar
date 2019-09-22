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

    //初始页面
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

    //去找回密码页面
    @RequestMapping(value = "toGetPass")
    public String toGetPass(){
        return "qianDuan/getpass";
    }

    @RequestMapping("tologin")
    public String login(){
        return "houtai/login";
    }
    @RequestMapping("toindex")
    public String index(){
        return "houtai/index";
    }
    @RequestMapping("welcome")
    public String welcome(){
        return "houtai/welcome";
    }

    @RequestMapping("admin-role")
    public String adminrole(){
        return "houtai/admin-role";
    }
    @RequestMapping("admin-cate")
    public String admincate(){
        return "houtai/admin-cate";
    }
    @RequestMapping("admin-rule")
    public String adminrule(){

        return "houtai/admin-rule";
    }

    @RequestMapping("admin-add")
    public String adminadd(){
        return "houtai/admin-add";
    }
}
