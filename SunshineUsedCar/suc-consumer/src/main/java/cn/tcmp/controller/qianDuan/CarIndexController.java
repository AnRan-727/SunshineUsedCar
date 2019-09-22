package cn.tcmp.controller.qianDuan;

import cn.tcmp.entity.CarPictures;
import cn.tcmp.entity.DataDictionary;
import cn.tcmp.entity.Vehicle;
import cn.tcmp.service.CarQicheService;
import cn.tcmp.service.dataDictionary.DataDictionaryService;
import cn.tcmp.service.indexCar.IndexCarService;
import cn.tcmp.service.pinyin.PinYinService;
import cn.tcmp.vo.CarTestingVO;
import cn.tcmp.vo.IndexVO;
import cn.tcmp.vo.jiance.CT1VO;
import cn.tcmp.vo.jiance.CT2VO;
import cn.tcmp.vo.jiance.CT3VO;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.sun.mail.imap.SortTerm;
import com.sun.mail.imap.protocol.ID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Reference
    private DataDictionaryService dataDictionaryService;
    @Reference
    private IndexCarService indexCarService;

    /**
     * 查询品牌到首页
     * @return
     */
    @RequestMapping(value = "queryVehicle",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String queryVehicle(){
        List<Object> arrayList = new ArrayList<>();
        //所有的品牌
        List<Vehicle> vehicles = qicheService.queryVehicleByVehicleName2("");

        /*for (Vehicle vehicle : vehicles) {
            System.err.println("品牌>>>>>"+vehicle);
        }*/
        //所有的车系
        List<Vehicle> list = qicheService.queryAllVehiclebyChexi();

        Map<String, List<Vehicle>> map = pinYinService.getMap(vehicles);

        /*for (Vehicle vehicle : list) {
            System.err.println("车系>>>>>"+vehicle);
        }

        for(Map.Entry<String, List<Vehicle>> entry : map.entrySet()){
            System.out.println("键 key ："+entry.getKey()+" 值value ："+entry.getValue());
        }*/

        //把品牌和车系放进集合里
        arrayList.add(list);
        arrayList.add(map);
        //返回
        return JSON.toJSONString(arrayList);
    }

    /**
     * 查询车型到首页
     * @return
     */
    @RequestMapping(value = "queryCarType",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String queryCarType(){
        DataDictionary dataDictionary = new DataDictionary();
        dataDictionary.setTypeCode("CAR_TYPE");
        List<DataDictionary> list = dataDictionaryService.querySingle(dataDictionary);
        return JSON.toJSONString(list);
    }

    /**
     * 查询汽车到首页
     * @return
     */
    @RequestMapping(value = "queryCar",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String queryCar(IndexVO indexVO){
        List<IndexVO> list = indexCarService.queryCar(indexVO);
        return JSON.toJSONString(list);
    }

    /**
     * 去汽车详情页
     * @param carId
     * @param model
     * @return
     */
    @RequestMapping(value = "queryCarInfo")
    public String queryCarInfo(Integer carId,Model model){
        //汽车详情
        CarTestingVO carTestingVO = indexCarService.detailCar(carId);
        System.err.println("详情----------"+carTestingVO);
        //图片
        List<CarPictures> carPictures = indexCarService.queryDetailImg(carId);
        for (CarPictures carPicture : carPictures) {
            System.err.println(carPicture);
        }
        //检测
        List<CT1VO> ct1VOS = indexCarService.queryJianCe(carId);
        for (CT1VO ct1VO : ct1VOS) {
            System.err.println("ct1VO"+ct1VO.getT1());
            for (CT2VO ct2VO : ct1VO.getCt2VO()) {
                System.err.println("ct2VO>>>>>>"+ct2VO.getT2());
                for (CT3VO ct3VO : ct2VO.getCt3VO()) {
                    System.err.println("ct3VO"+ct3VO.getT3());
                }
            }
        }
        return "qianDuan/infor";
    }

}
