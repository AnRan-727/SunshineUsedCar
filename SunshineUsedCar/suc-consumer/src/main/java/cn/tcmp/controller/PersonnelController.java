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
        System.err.println(loginUser);
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
        System.err.println(tokenDto.getErrorcode());
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
    @RequestMapping("admin-list")
    public String personnelList(Integer pageNumber, Integer pageSize, Personnel personnel, Model model){
        PageUtils<Personnel> personnelList=personnelvService.personnelList(pageNumber,pageSize,personnel);
        model.addAttribute("perList",personnelList);
        model.addAttribute("Personnel",personnel);
        return "houtai/admin-list";
    }

    //工作人员分页
    @ResponseBody
    @RequestMapping(value = "ajax-admin-list",method = RequestMethod.GET,
            produces ={"application/json;charset=UTF-8"})
    public String ajaxpersonnelList(Integer pageNumber, Integer pageSize, Personnel personnel, Model model){
        PageUtils<Personnel> personnelList=personnelvService.personnelList(pageNumber,pageSize,personnel);
        return JSON.toJSONString(personnelList);
    }

    //去工作人员修改页
    @RequestMapping("admin-edit")
    public String adminedit(Personnel personnel,Model model){
        System.out.println(personnel);
        Personnel personnel1=this.personnelvService.PersonnelLogin(personnel);
        model.addAttribute("personnel",personnel1);
        return "houtai/admin-edit";
    }
    //做工作人员的修改操作
    @ResponseBody
    @RequestMapping("toPersonnelUpdate")
    public String toPersonnelUpdate(Personnel personnel){
        int count=this.personnelvService.PersonnelUpdate(personnel);
        if (count<1){
            return "erroe";
        }
        return "success";
    }
    //做工作人员删除操作
    @ResponseBody
    @RequestMapping("PersonnelDelete")
    public String PersonnelDelete(Integer id,Model model,Integer[] ids){
        System.err.println(id);
        System.err.println("ids+++"+ids.length);
        if (ids!=null || (ids==null && ids.length!=0)){
            int counts=0;
            for (int i=0;i<ids.length;i++){
                System.err.println(ids[i]);
                int count=this.personnelvService.PersonnelDelete(ids[i]);
                counts=counts+count;
                System.err.println("count::"+count);
                System.err.println("counts:"+counts);
            }
            if (counts==ids.length){
                return "success";
            }
        }
        return "error";

    }//做工作人员删除操作
    @ResponseBody
    @RequestMapping("dangePersonnelDelete")
    public String dangePersonnelDelete(Integer id,Model model){
        System.err.println(id);
            int count=this.personnelvService.PersonnelDelete(id);
            if (count<1){
                return "erroe";
            }
        return "success";
    }

     //做工作人员添加操作
     @ResponseBody
     @RequestMapping("doPersonnelAdd")
     public String dodangePersonnelAdd(Personnel personnel){
         int count=this.personnelvService.PersonnelAdd(personnel);
         if (count<1){
             return "erroe";
         }
         return "success";
     }
}
