package cn.tcmp.controller.qianDuan;

import cn.tcmp.dto.TokenDto;
import cn.tcmp.entity.CarPictures;
import cn.tcmp.entity.DataDictionary;
import cn.tcmp.entity.Vehicle;
import cn.tcmp.service.CarQicheService;
import cn.tcmp.service.CarUserService;
import cn.tcmp.service.CreatRedisService;
import cn.tcmp.service.TokenService;
import cn.tcmp.service.dataDictionary.DataDictionaryService;
import cn.tcmp.service.indexCar.IndexCarService;
import cn.tcmp.service.pinyin.PinYinService;
import cn.tcmp.util.PageUtils;
import cn.tcmp.vo.CarTestingVO;
import cn.tcmp.vo.ChaXunVO;
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

import javax.servlet.http.HttpServletRequest;
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
    @Reference
    private TokenService tokenService;
    @Reference
    private CarUserService carUserService;
    @Reference
    private CreatRedisService redisService;
    @Reference
    private DataDictionaryService dictionaryService;

    /**
     * 去我要卖车页面
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "toWoYaoMaiChe")
    public String toWoYaoMaiChe( Model model, HttpServletRequest request){
        //验证Token
        TokenDto tokenDto = new TokenDto();
        String token = request.getHeader("Cookie");
        if(token != null && token != ""){
            //1.验证Token
            String[] idStr = token.split("-");
            Integer id = Integer.parseInt(idStr[2]);
            String[] tkStr = token.split(";");
            token = tkStr[0].substring(6);
            System.err.println("Token>>>>>>"+token);
            Boolean result=tokenService.checkToken(token,
                    request.getHeader("User-Agent"));
            if(result){
                //用户
                model.addAttribute("carUserName",carUserService.detailCarUser(id));
                /*System.out.println("-------------"+carUserService.detailCarUser(id));*/
            }
        }
        return "qianDuan/wymc";
    }

    /**
     * 查询品牌 车系
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
     * 根据品牌id查询车系
     * @param id
     * @return
     */
    @RequestMapping(value = "queryVehicleByPinPai",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String queryVehicleByPinPai(Integer id){
        List<Vehicle> list = indexCarService.queryVehicleByPinPai(id);
        /*System.out.println("车系"+list);*/
        return JSON.toJSONString(list);
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
     * 去汽车展示页面
     * @return
     */
    @RequestMapping(value = "toCarList")
    public String toCarList(ChaXunVO chaXunVO,Model model, HttpServletRequest request){
        //验证Token
        TokenDto tokenDto = new TokenDto();
        String token = request.getHeader("Cookie");
        if(token != null && token != ""){
            //1.验证Token
            String[] idStr = token.split("-");
            Integer id = Integer.parseInt(idStr[2]);
            String[] tkStr = token.split(";");
            token = tkStr[0].substring(6);
            System.err.println("Token>>>>>>"+token);
            Boolean result=tokenService.checkToken(token,
                    request.getHeader("User-Agent"));
            if(result){
                model.addAttribute("carUser",carUserService.detailCarUser(id));
            }
        }
        //数据字典
        this.getDataDictionary(model);
        System.out.println(chaXunVO);
        PageUtils<IndexVO> pageUtils = indexCarService.queryAllCar(chaXunVO, 1, 12);
        model.addAttribute("list",pageUtils);
        /*System.out.println("-------------+++++++++++"+pageUtils.getList());*/
        return "qianDuan/list";
    }

    /**
     * 去汽车详情页
     * @param carId
     * @param model
     * @return
     */
    @RequestMapping(value = "queryCarInfo")
    public String queryCarInfo(Integer carId, Model model, HttpServletRequest request){
        //验证Token
        TokenDto tokenDto = new TokenDto();
        String token = request.getHeader("Cookie");
        if(token != null && token != ""){
            //1.验证Token
            String[] idStr = token.split("-");
            Integer id = Integer.parseInt(idStr[2]);
            String[] tkStr = token.split(";");
            token = tkStr[0].substring(6);
            System.err.println("Token>>>>>>"+token);
            Boolean result=tokenService.checkToken(token,
                    request.getHeader("User-Agent"));
            if(result){
                //用户
                model.addAttribute("carUser",carUserService.detailCarUser(id));
                //是否已收藏
                model.addAttribute("IsNo",indexCarService.queryIsNoCollection(id,carId));
                System.out.println("-------------"+carUserService.detailCarUser(id));
            }
        }
        //汽车详情
        CarTestingVO carTestingVO = indexCarService.detailCar(carId);
        System.err.println("详情----------"+carTestingVO);
        model.addAttribute("car",carTestingVO);
        //图片
        List<CarPictures> carPictures = indexCarService.queryDetailImg(carId);
        for (CarPictures carPicture : carPictures) {
            System.err.println(carPicture);
        }
        model.addAttribute("img",carPictures);
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
        model.addAttribute("list",ct1VOS);
        //汽车顾问

        PageUtils<IndexVO> pageUtils = indexCarService.queryGuWenCar(carTestingVO.getPersonnelID(), 1, 5);
        System.out.println("--汽车顾问--"+carTestingVO.getPersonnelID());
        model.addAttribute("guwen",pageUtils);
        return "qianDuan/infor";
    }

    /**
     * ajax各种条件查询汽车信息
     * @param pageNum
     * @param pageSize
     * @param chaXunVO 查询条件实体类
     * @return
     */
    @RequestMapping(value = "ajaxQueryAllCar",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String ajaxQueryAllCar(Integer pageNum,Integer pageSize,ChaXunVO chaXunVO){
        System.out.println("查询条件是+++++++++++++++:"+chaXunVO);
        PageUtils<IndexVO> pageUtils = indexCarService.queryAllCar(chaXunVO, pageNum, pageSize);
        /*for (IndexVO vo : pageUtils.getList()) {
            System.err.println(vo);
        }*/
        return JSON.toJSONString(pageUtils);
    }


    /**
     * 收藏汽车
     * @return
     */
    @RequestMapping(value = "userCollection",method = RequestMethod.POST)
    @ResponseBody
    public String userCollection(Integer carID,Integer userID,HttpServletRequest request){
        //1.获取UserAgent
        String userAgent = request.getHeader("User-Agent");
        //2.截取Token
        String token = request.getHeader("Cookie");
        String[] tkStr = token.split(";");
        token = tkStr[0].substring(6);
        System.err.println("Token>>>>>>"+token);
        //3.验证Token
        if(!tokenService.checkToken(token,userAgent)){
            return "false";
        }
        //收藏操作
        Integer num = indexCarService.userCollection(carID,userID);

        return "true";
    }

    /**
     * 取消收藏
     * @param cID
     * @return
     */
    @RequestMapping(value = "deleteCollection",method = RequestMethod.POST)
    @ResponseBody
    public String deleteCollection(Integer cID){
        indexCarService.deleteCollection(cID);
        return "true";
    }


    /**
     * 去顾问页面
     * @param gwid 顾问ID
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "toGuWenCar")
    public String toGuWenCar(Integer gwid ,Model model, HttpServletRequest request){
        //获取Token
        TokenDto tokenDto = new TokenDto();
        String token = request.getHeader("Cookie");
        if(token != null && token != ""){
            //1.验证Token
            String[] idStr = token.split("-");
            Integer id = Integer.parseInt(idStr[2]);
            String[] tkStr = token.split(";");
            token = tkStr[0].substring(6);
            System.err.println("Token>>>>>>"+token);
            Boolean result=tokenService.checkToken(token,
                    request.getHeader("User-Agent"));
            if(result){
                //用户
                model.addAttribute("carUser",carUserService.detailCarUser(id));
            }
        }
        //顾问名下的车
        PageUtils<IndexVO> list = indexCarService.queryGuWenCar(gwid, 1, 5);

        for (IndexVO a : list.getList()) {
            System.out.println("----"+a);
        }

        model.addAttribute("list",list);
        //数据字典
        this.getDataDictionary(model);

        return "qianDuan/shoper";
    }
    //数据字典
    private void getDataDictionary(Model model) {
        //数据字典
        DataDictionary dataDictionary = new DataDictionary();
        //车型
        dataDictionary.setTypeCode("CAR_TYPE");
        List<DataDictionary> di = dictionaryService.querySingle(dataDictionary);
        /*System.out.println("车型-------"+di);*/
        model.addAttribute("di",di);
        //燃油类型
        dataDictionary.setTypeCode("FUEL");
        List<DataDictionary> dir = dictionaryService.querySingle(dataDictionary);
        /*System.out.println("燃油-------"+di);*/
        model.addAttribute("dir",dir);
        //座位数
        dataDictionary.setTypeCode("SEAT");
        List<DataDictionary> diz = dictionaryService.querySingle(dataDictionary);
        /*System.out.println("座位数-------"+diz);*/
        model.addAttribute("diz",diz);
        //变速箱
        dataDictionary.setTypeCode("GEARBOX");
        List<DataDictionary> dib = dictionaryService.querySingle(dataDictionary);
        /*System.out.println("变速箱-------"+dib);*/
        model.addAttribute("dib",dib);
        //排放标准
        dataDictionary.setTypeCode("EMISSION");
        List<DataDictionary> dip = dictionaryService.querySingle(dataDictionary);
        /*System.out.println("排放标准-------"+dip);*/
        model.addAttribute("dip",dip);
        //颜色
        dataDictionary.setTypeCode("COLOR");
        List<DataDictionary> diy = dictionaryService.querySingle(dataDictionary);
        /*System.out.println("颜色-------"+diy);*/
        model.addAttribute("diy",diy);
        //国别
        dataDictionary.setTypeCode("COUNTRY");
        List<DataDictionary> dic = dictionaryService.querySingle(dataDictionary);
        /*System.out.println("国别-------"+dic);*/
        model.addAttribute("dic",dic);
    }



}
