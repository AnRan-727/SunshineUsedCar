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

}
