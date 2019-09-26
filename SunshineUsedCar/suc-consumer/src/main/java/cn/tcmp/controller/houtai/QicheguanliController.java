package cn.tcmp.controller.houtai;

import cn.tcmp.entity.Car;
import cn.tcmp.entity.DataDictionary;
import cn.tcmp.entity.Personnel;
import cn.tcmp.entity.Vehicle;
import cn.tcmp.service.CarQicheService;
import cn.tcmp.service.xialakuang.XialakuangService;
import cn.tcmp.util.PageQiche;
import cn.tcmp.vo.CarVO;
import cn.tcmp.vo.CarVO2;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class QicheguanliController {

    @Reference
    private CarQicheService carQicheService;
    @Reference
    private XialakuangService xialakuangService;

    @ResponseBody
    @RequestMapping(value = "ajaxQichelist", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public  String ajaxqiche(String name,Integer pageNum, Integer pageSize, Model model) {
        if (pageNum == null) {
            pageNum=1;
        }
        if (pageSize == null) {
            pageSize=5;
        }
        PageQiche<CarVO> pageQiche = carQicheService.qeuryCarByVehicleChexiname(name,pageNum, pageSize);

        return JSON.toJSONString(pageQiche);
    }
    //    跳转到车辆信息页面
    @RequestMapping("tocheshiyemian")
    public String tocheshiyemian() {
        return "houtai/qiche/IindividualTravelers";
    }
    //    跳转到车辆增加信息页面
    @RequestMapping("toInsertAAAAAA")
    public String toInsertAAAAAA() {
        return "houtai/qiche/insertIindividualTravelers";
    }
    //    查询车辆详细信息
    @ResponseBody
    @RequestMapping(value = "ajaxDetailCar", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public  String ajaxDetailCar(Integer carID) {
        CarVO2 carVO = carQicheService.detailCar(carID);
        return JSON.toJSONString(carVO);
    }
    //    删除车辆信息
    @ResponseBody
    @RequestMapping(value = "ajaxDeleteCar", method = RequestMethod.POST)
    public boolean ajaxDeleteCar(Integer carID) {
        Integer integer = carQicheService.deleteCar(carID);
        return integer>0?true:false;
    }
    /*//  加载下拉框
    @ResponseBody
    @RequestMapping(value = "ajaxXiaLaKuang", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String ajaxDeleteCar(String typeCode) {
        List<DataDictionary> dataDictionaries = xialakuangService.queryAllDataDictionary(typeCode);
        return JSON.toJSONString(dataDictionaries);
    }
    //  加载下拉框品牌
    @ResponseBody
    @RequestMapping(value = "ajaxQueeyPinPai", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String ajaxQueeyPinPai() {
        List<Vehicle> vehicles = xialakuangService.queryAllVehicle2();
        return JSON.toJSONString(vehicles);
    }
    //  加载根据车辆id查询下拉框车系
    @ResponseBody
    @RequestMapping(value = "ajaxQueeyCheXi", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String ajaxQueeyPinPai(Integer vehicleID) {
        List<Vehicle> vehicles = carQicheService.queryVehicleByVehicleName(vehicleID);
        return JSON.toJSONString(vehicles);
    }
    //  加载下拉框车系
    @ResponseBody
    @RequestMapping(value = "ajaxQueeyCheXiQuanbu", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String ajaxQueeyCheXiQuanbu() {
        List<Vehicle> vehicles = xialakuangService.queryAllCheXi();
        return JSON.toJSONString(vehicles);
    }
    //  加载下拉框所有的员工
    @ResponseBody
    @RequestMapping(value = "ajaxYuanGong", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String ajaxYuanGong() {
        List<Personnel> personnels = xialakuangService.queryAllYuanGong();
        return JSON.toJSONString(personnels);
    }
    //  修改车辆信息
    @ResponseBody
    @RequestMapping(value = "ajaxUpdateQiche", method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    public boolean ajaxUpdateQiche(Car c) {
        System.err.println(c);
        Integer integer = carQicheService.updateCar(c);
        return integer>0?true:false;
    }*/

    //  加载下拉框
    @ResponseBody
    @RequestMapping(value = "ajaxXiaLaKuang", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String ajaxDeleteCar(String typeCode) {
        List<DataDictionary> dataDictionaries = xialakuangService.queryAllDataDictionary(typeCode);
        return JSON.toJSONString(dataDictionaries);
    }
    //  加载下拉框品牌
    @ResponseBody
    @RequestMapping(value = "ajaxQueeyPinPai", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String ajaxQueeyPinPai() {
        List<Vehicle> vehicles = xialakuangService.queryAllVehicle2();
        return JSON.toJSONString(vehicles);
    }
    //  加载根据车辆id查询下拉框车系
    @ResponseBody
    @RequestMapping(value = "ajaxQueeyCheXi", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String ajaxQueeyPinPai(Integer vehicleID) {
        List<Vehicle> vehicles = carQicheService.queryVehicleByVehicleName(vehicleID);
        return JSON.toJSONString(vehicles);
    }
    //  加载下拉框车系
    @ResponseBody
    @RequestMapping(value = "ajaxQueeyCheXiQuanbu", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String ajaxQueeyCheXiQuanbu() {
        List<Vehicle> vehicles = xialakuangService.queryAllCheXi();
        return JSON.toJSONString(vehicles);
    }
    //  加载下拉框所有的员工
    @ResponseBody
    @RequestMapping(value = "ajaxYuanGong", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String ajaxYuanGong() {
        List<Personnel> personnels = xialakuangService.queryAllYuanGong();
        return JSON.toJSONString(personnels);
    }
    //  修改车辆信息
    @ResponseBody
    @RequestMapping(value = "ajaxUpdateQiche", method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    public boolean ajaxUpdateQiche(Car c) {
        System.err.println(c);
        Integer integer = carQicheService.updateCar(c);
        return integer>0?true:false;
    }


}
