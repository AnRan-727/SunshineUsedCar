package cn.tcmp.controller.carUser;

import cn.tcmp.dto.DataDto;
import cn.tcmp.dto.TokenDto;
import cn.tcmp.entity.CarUser;
import cn.tcmp.service.CarUserService;
import cn.tcmp.service.TokenService;
import cn.tcmp.util.Common;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/9
 */
@Controller
@RequestMapping(value = "carUser")
public class CarUserController {

    @Reference
    private CarUserService carUserService;
    @Reference
    private TokenService tokenService;

    //注册
    @RequestMapping("doZhucheController")
    @ResponseBody
    public String doZhucheController(CarUser user, HttpServletRequest request, Model model) {

        System.out.println("进来了注册++"+user);
        Integer count=carUserService.carUserRegister(user);
        CarUser uss=carUserService.existCarUser(user.getUserPhone());
        if(count >0) {
            String login = this.login(request,user);
            System.err.println("登录返回值:"+login);
            return login;
        }else {
            return "注册失败!";
        }
    }


    //ajax验证用户名是否存在
    @RequestMapping(value = "ajaxUserphoneController",method = RequestMethod.POST)
    @ResponseBody
    public boolean  ajaxUserphoneController(String phone) {
        System.err.println("手机号:"+phone);
        CarUser user=carUserService.existCarUser(phone);
        System.err.println("验证手机号是否存在:"+user);
        if(user !=null) {
            return true;
        }else {
            return false;
        }
    }


    //退出
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        String agent = request.getHeader("User-Agent");
        String token = request.getHeader("Cookie");
        String[] tkStr = token.split(";");
        token = tkStr[0].substring(6);
        System.err.println("Token>>>>>>"+token);
        TokenDto tokenDto = tokenService.logout(token, agent);
        return "redirect:/";
    }

    //用户登录
    @RequestMapping(value = "login",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String login(HttpServletRequest request, CarUser user) {
        System.out.println("登录进来+CarUser类>>>>>>>>>>"+user);

        TokenDto tokenDto=new TokenDto();
        DataDto data=null;

        //1. 用户名密码验证
        CarUser loginUser = carUserService.carUserLogin(user);
        if (null == loginUser) {
            //用户名密码错误
            tokenDto.setErrorcode(Common.CODE_02);
            return JSON.toJSONString(tokenDto);
        }
        //2. 生成token
        String token=tokenService.
                createToken(request.getHeader("User-Agent"),loginUser);
        //3. 保存token
        tokenService.saveToken(token,loginUser);

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
        List<Object> list = new ArrayList<>();
        list.add(tokenDto);
        list.add(loginUser);

        String tokenStr = JSON.toJSONString(list);

        System.out.println("tokenStr"+tokenStr);

        return tokenStr;
    }

    //去个人信息页面
    @RequestMapping(value = "toCarUserInfo")
    public String toCarUserInfo(Integer userID,Model model,HttpServletRequest request){
        //1.获取UserAgent
        String userAgent = request.getHeader("User-Agent");
        //2.截取Token
        String token = request.getHeader("Cookie");
        String[] tkStr = token.split(";");

        token = tkStr[0].substring(6);
        //查看输出截取的token(redis KEY)
        System.err.println("Token>>>>>>"+token);
        //3.验证Token
        if(!tokenService.checkToken(token,userAgent)){
            return "qianDuan/404";
        }
        //4.查询个人信息
        model.addAttribute("carUser",carUserService.detailCarUser(userID));

        //TODO 查询收藏的汽车,浏览过的汽车
        return "qianDuan/huiyuanzhongxin";
    }

    //去修改个人信息
    @RequestMapping(value = "toHuiYuanZhangHu")
    public String toHuiYuanZhangHu(Integer userID,Model model,HttpServletRequest request){
        //1.获取UserAgent
        String userAgent = request.getHeader("User-Agent");
        //2.截取Token
        String token = request.getHeader("Cookie");
        String[] tkStr = token.split(";");
        token = tkStr[0].substring(6);
        System.err.println("Token>>>>>>"+token);
        //3.验证Token
        if(!tokenService.checkToken(token,userAgent)){
            return "qianDuan/404";
        }
        //4.查询个人信息
        model.addAttribute("carUser",carUserService.detailCarUser(userID));

        return "qianDuan/huiyuanzhanghu";
    }

    //修改个人信息
    @RequestMapping(value = "doUpdateCarUser")
    public String doUpdateCarUser(CarUser carUser,Model model,HttpServletRequest request){
        //1.获取Token
        String userAgent = request.getHeader("User-Agent");
        String token = request.getHeader("Cookie");
        String[] idStr = token.split("-");
        String[] tkStr = token.split(";");
        token = tkStr[0].substring(6);
        //2.验证Token
        if(!tokenService.checkToken(token,userAgent)){
            return "qianDuan/404";
        }
        //3.修改个人信息
        System.err.println("修改用户>>>>>>>"+carUser);
        carUserService.updateCarUser(carUser);
        //4.查询个人信息
        Integer id = Integer.parseInt(idStr[2]);
        model.addAttribute("carUser",carUserService.detailCarUser(id));
        //TODO 查询收藏的汽车,浏览过的汽车

        return "qianDuan/huiyuanzhongxin";
    }



}
