package cn.tcmp.controller.carUser;

import cn.tcmp.dto.DataDto;
import cn.tcmp.dto.TokenDto;
import cn.tcmp.entity.CarUser;
import cn.tcmp.service.CarUserService;
import cn.tcmp.service.CreatRedisService;
import cn.tcmp.service.MailService;
import cn.tcmp.service.TokenService;
import cn.tcmp.service.indexCar.IndexCarService;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

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
    @Reference
    private CreatRedisService redisService;
    @Reference
    private MailService mailService;
    @Reference
    private IndexCarService indexCarService;


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
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>carUSer"+carUserService.detailCarUser(userID));
        //5.收藏的汽车
        model.addAttribute("coll",indexCarService.queryCollection(userID));

        //TODO 查询浏览过的汽车



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
    //去需求
    @RequestMapping(value = "toXuQiu")
    public String toXuQiu(Integer userID,HttpServletRequest request,Model model){

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
        //5.收藏的汽车
        model.addAttribute("coll",indexCarService.queryCollection(userID));

        return "qianDuan/huiyuanxuqiu";
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

    //发送邮箱验证码
    @RequestMapping(value = "getCode")
    @ResponseBody
    public String getCode(String email){
        //生成激活码
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        uuid = uuid.replaceAll("-", "");
        uuid = uuid.substring(0,6);
        System.err.println("uuid>>>>>>>"+uuid);
        if(email != null){

            redisService.set("uuid",uuid,Common.REDIS_TIMEOUT);
        }
        //uuid发送邮箱
        mailService.sendHtmlMail(email,"尊敬的客户您好:","您的激活码是:"+uuid);

        return "请在两分钟内输入验证码";
    }

    //验证邮箱验证码
    @RequestMapping(value = "testing")
    @ResponseBody
    public String testing(CarUser carUser){
        //从redis获取uuid
        String uuid = redisService.get("uuid").toString();
        System.out.println("验证邮箱>>>>>>"+uuid+">>>>"+carUser.getCode()+">>>>"+carUser);
        //判断输入uuid是否正确
        if(!uuid.equals(carUser.getCode())){

            redisService.delete("uuid");
            this.getCode(carUser.getUserEmail());
            return "验证码输入错误,已重新发送邮件!请输入!";
        }
        Integer num = carUserService.updateUserPhone(carUser);
        System.err.println(num);
        return "true";
    }

    @RequestMapping("member-list")
    public String memberlist(Integer pageNumber, Integer pageSize, CarUser carUser, Model model){
        PageUtils<CarUser> list=this.carUserService.carUserQuery(pageNumber,pageSize,carUser);
        model.addAttribute("userlist",list);
        model.addAttribute("carUser",carUser);
        return "houtai/member-list";
    }
    @ResponseBody
    @RequestMapping(value = "ajaxmember-list",method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public String ajaxmemberlist(Integer pageNum, Integer pageSize, CarUser carUser, Model model){
        PageUtils<CarUser> CarUserlist=this.carUserService.carUserQuery(pageNum,pageSize,carUser);
        return JSON.toJSONString(CarUserlist);
    }

    @RequestMapping("member-add")
    public String memberadd(CarUser carUser){
        return "member-add";
    }
    //查询客户详情
    @ResponseBody
    @RequestMapping("DetailCarUser")
    public String DetailCarUser(Integer userid){
        CarUser carUser=this.carUserService.detailCarUser(userid);
        return JSON.toJSONString(carUser);
    }
    //注销用户
    @ResponseBody
    @RequestMapping("AjaxDeleteCarUser")
    public boolean deleteCarUser(Integer userid){
        Integer count=this.carUserService.carUserDelete(userid);
        return count>0?true:false;
    }
    @ResponseBody
    @RequestMapping("AjaxUpdateCarUser")
    public boolean  UpdateCarUser(CarUser carUser)
    {
        Integer count=this.carUserService.updateCarUser2(carUser);
        return count>0?true:false;
    }
}
