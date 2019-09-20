package cn.tcmp.controller.qianDuan;

import cn.tcmp.entity.Vehicle;
import cn.tcmp.service.CarQicheService;
import cn.tcmp.service.pinyin.PinYinService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/20
 */
@Controller
@RequestMapping(value = "index")
public class CarIndexController {

    @Reference
    private CarQicheService qicheService;
    @Reference
    private PinYinService pinYinService;

    @RequestMapping(value = "queryVehicle",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String queryVehicle(){
        List<Object> arrayList = new ArrayList<>();
        //所有的品牌
        List<Vehicle> vehicles = qicheService.queryVehicleByVehicleName2("");

        for (Vehicle vehicle : vehicles) {
            System.err.println("品牌>>>>>"+vehicle);
        }
        //所有的车系
        List<Vehicle> list = qicheService.queryAllVehiclebyChexi();

        Map<String, List<Vehicle>> map = pinYinService.getMap(vehicles);

        for (Vehicle vehicle : list) {
            System.err.println("车系>>>>>"+vehicle);
        }

        for(Map.Entry<String, List<Vehicle>> entry : map.entrySet()){
            System.out.println("键 key ："+entry.getKey()+" 值value ："+entry.getValue());
        }

        //把品牌和车系放进集合里
        arrayList.add(list);
        arrayList.add(map);
        //返回
        return JSON.toJSONString(arrayList);
    }



}
