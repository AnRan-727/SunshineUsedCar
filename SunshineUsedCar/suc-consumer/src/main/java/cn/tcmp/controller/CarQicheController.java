package cn.tcmp.controller;

import cn.tcmp.entity.Car;
import cn.tcmp.entity.Vehicle;
import cn.tcmp.service.CarQicheService;
import cn.tcmp.util.PageQiche;
import cn.tcmp.vo.CarVO;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 查询list车controller
 */
@Controller
public class CarQicheController {

    @Reference
    private CarQicheService carQicheService;

    /**
     * 跳转汽车信息显示页面
     */
    @RequestMapping("toListQiche")
    public String toListQiche(Integer pageNum,Integer pageSize,Model model) {
        if (pageNum == null) {
            pageNum=1;
        }
        if (pageSize == null) {
            pageSize=8;
        }
        PageQiche<CarVO> pageQiche = carQicheService.queryAllQiche(pageNum, pageSize);
        System.err.println(pageQiche);

        model.addAttribute("carlist",pageQiche);
        return "qianDuan/list";
    }
    //ajax分页
    @ResponseBody
    @RequestMapping(value = "ajaxQicheFenye", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public PageQiche<CarVO> ajaxQicheFenye(Integer pageNum, Integer pageSize){
        System.err.println("传过来的pageNum值为++++++++++++++++++"+pageNum);
        if (pageNum == null) {
            pageNum=1;
        }
        if (pageSize == null) {
            pageSize=8;
        }
        PageQiche<CarVO> pageQiche = carQicheService.queryAllQiche(pageNum, pageSize);
        System.err.println("值为++++++++++++++++++"+pageQiche);
        return pageQiche;
    }
    /**
     * 查询全的车的ajax方法 （关）
     * @param car 查询条件
     * @param pageNum 分页当前页
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "ajaxQueryAllCarQiche", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public String ajaxQueryAllCarQiche(Car car, Integer pageNum, Integer pageSize){
        PageQiche<CarVO> pageQiche = carQicheService.queryPageCarVo(car, pageNum, pageSize);
        return JSON.toJSONString(pageQiche);
    }

    /**
     * 加载第一梯队车类品牌 （关）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "ajaxLoadCarQiche", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public String ajaxLoadCarQiche(){
        List<Vehicle> vehicles = carQicheService.queryAllVehicle();
        return JSON.toJSONString(vehicles);
    }
    //查询相对应的车系
    @ResponseBody
    @RequestMapping(value = "ajaxLoadCarQicheChexi", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public String ajaxLoadCarQicheChexi(Integer data){
        List<Vehicle> vehicles = carQicheService.queryVehicleByVehicleName(data);
        return JSON.toJSONString(vehicles);
    }
    /**
     * 模糊查询车辆品牌
     * @return
     */
    @RequestMapping(value = "ajaxQueryCarChepinpaiByVehicalname",method = RequestMethod.GET)
    @ResponseBody
    public List<Vehicle> ajaxQueryCarChepinpaiByVehicalname(String vehicleName) {
        List<Vehicle> vehicles = carQicheService.queryVehicleByVehicleName2(vehicleName);
        return vehicles;
    }
    /**
     * 显示车辆品牌在输入框里
     * @return
     */
    @RequestMapping(value = "ajaxQueryCarByVehicleId",method = RequestMethod.GET)
    @ResponseBody
    public Vehicle ajaxQueryCarByVehicleId(Integer vehicleID) {
        Vehicle vehicle = carQicheService.detailVehicleById(vehicleID);
        return vehicle;
    }
    /**
     * 搜索查询并在页面上显示
     * @return
     */
    @RequestMapping("doQueryListCheche")
    public String doQueryListChe(String vehicleName,Integer pageNum,Integer pageSize, Model model) {
        System.err.println("++++++++++++++传过来的车系名字"+vehicleName);
        if (pageNum == null) {
                pageNum=1;
        }
        if (pageSize == null) {
            pageSize=8;
        }
        PageQiche<CarVO> pageQiche = carQicheService.qeuryCarByVehicleChexiname(vehicleName, pageNum, pageSize);
        System.err.println(pageQiche);

        model.addAttribute("carlist",pageQiche);
        return "qianDuan/list";
    }


    @RequestMapping("tocheshi")
    public String tocheshi(){
        return "qianDuan/cheshiyemian";
    }

}
