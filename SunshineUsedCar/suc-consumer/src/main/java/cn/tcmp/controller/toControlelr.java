package cn.tcmp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/9
 */
@Controller
public class toControlelr {
    @RequestMapping("/")
    public String to(){
        return "qianDuan/index";
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
