package cn.tcmp.controller;

import cn.tcmp.dto.DataDto;
import cn.tcmp.dto.TokenDto;
import cn.tcmp.entity.Personnel;
import cn.tcmp.service.HouTaiTokenService;
import cn.tcmp.service.PersonnelvService;
import cn.tcmp.util.Common;
import cn.tcmp.util.PageUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Controller
@RequestMapping("houtai")
public class PersonnelController {
    @Reference
    private PersonnelvService personnelvService;

    @Reference
    private HouTaiTokenService houTaiTokenService;



    //用户登录
    @ResponseBody
    @RequestMapping(value = "login",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public String login(HttpServletRequest request, Personnel user) {
        TokenDto tokenDto=new TokenDto();
        DataDto data=null;

        //1. 用户名密码验证
        Personnel loginUser = personnelvService.PersonnelLogin(user);
        if (null == loginUser) {
            //用户名密码错误
            tokenDto.setErrorcode(Common.CODE_02);
            return JSON.toJSONString(tokenDto);
        }

        //2. 生成token
        String token=houTaiTokenService.
                createToken(request.getHeader("User-Agent"),loginUser);
        //3. 保存token
        houTaiTokenService.saveToken(token,loginUser);

        //4.将token信息返回给用户(TokenDto ->json)
        data=new DataDto();
        data.setToken(token);
        //token的生成时间
        data.setGenTime(Calendar.getInstance().getTimeInMillis()+"");
        //token的过期时间
        data.setExpTime(Calendar.getInstance().getTimeInMillis()
                +Common.TOKEN_TIMEOUT+"");
        tokenDto.setData(data);
        tokenDto.setErrorcode(Common.CODE_SUCCESS);
        return JSON.toJSONString(tokenDto);
    }

    //退出
    @ResponseBody
    @RequestMapping(value = "logout",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String logout(HttpServletRequest request) {
        String agent = request.getHeader("User-Agent");
        String token = request.getHeader("token");
        TokenDto tokenDto = houTaiTokenService.logout(token, agent);
        return JSON.toJSONString(tokenDto);
    }


    //去工作人员管理页(查询所有工作人员)
    @ResponseBody
    @RequestMapping("admin-list1")
    public String personnelList1(Integer pageNumber, Integer pageSize, Personnel personnel, Model model){
        System.err.println("personnel++"+personnel);
        PageUtils<Personnel> personnelList=personnelvService.personnelList(pageNumber,pageSize,personnel);
        System.err.println("工作人员:"+personnelList);
        model.addAttribute("perList",personnelList);
        //return "houtai/admin-list";
        return JSON.toJSONString(personnelList);
    }
    @RequestMapping("admin-list")
    public String personnelList(Integer pageNumber, Integer pageSize, Personnel personnel, Model model){
        System.err.println("personnel++"+personnel);
        PageUtils<Personnel> personnelList=personnelvService.personnelList(pageNumber,pageSize,personnel);
        System.err.println("工作人员:"+personnelList);
        model.addAttribute("perList",personnelList);
        return "houtai/admin-list";
    }
}
