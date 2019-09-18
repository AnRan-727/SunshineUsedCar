package cn.tcmp.controller;

import cn.tcmp.entity.Car;
import cn.tcmp.entity.Vehicle;
import cn.tcmp.service.CarQicheService;
import cn.tcmp.util.PageQiche;
import cn.tcmp.vo.CarVO;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;
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
     * 搜索跳转
     */
    @RequestMapping("toListQiche")
    public String toListQiche() {
        return "qianDuan/list";
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
    @RequestMapping(value = "ajaxLoadCarQiche", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public String ajaxLoadCarQiche(){
        List<Vehicle> vehicles = carQicheService.queryAllVehicle();
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


}
