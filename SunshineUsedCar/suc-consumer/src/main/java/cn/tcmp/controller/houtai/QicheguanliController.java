package cn.tcmp.controller.houtai;

import cn.tcmp.service.CarQicheService;
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

@Controller
public class QicheguanliController {

    @Reference
    private CarQicheService carQicheService;

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
        System.err.println(pageQiche);

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
    @ResponseBody
    @RequestMapping(value = "ajaxDetailCar", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public  String ajaxDetailCar(Integer carID) {
        CarVO2 carVO = carQicheService.detailCar(carID);
        return JSON.toJSONString(carVO);
    }
    @ResponseBody
    @RequestMapping(value = "ajaxDeleteCar", method = RequestMethod.POST)
    public boolean ajaxDeleteCar(Integer carID) {
        Integer integer = carQicheService.deleteCar(carID);
        return integer>0?true:false;
    }




}
