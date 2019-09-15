package cn.tcmp.controller.carUser;

import cn.tcmp.dto.DataDto;
import cn.tcmp.dto.TokenDto;
import cn.tcmp.entity.CarUser;
import cn.tcmp.service.CarUserService;
import cn.tcmp.service.TokenService;
import cn.tcmp.util.Common;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

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
            return this.login(request,user);
        }else {
            return "注册失败!";
        }
    }


    //ajax验证用户名是否存在
    @RequestMapping("ajaxUserphoneController")
    @ResponseBody
    public boolean  ajaxUserphoneController(String phone) {
        CarUser user=carUserService.existCarUser(phone);
        if(user !=null) {
            return true;
        }else {
            return false;
        }
    }


    //用户登录
    @RequestMapping(value = "login",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String login(HttpServletRequest request, CarUser user) {

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

        return JSON.toJSONString(tokenDto);
    }




}
