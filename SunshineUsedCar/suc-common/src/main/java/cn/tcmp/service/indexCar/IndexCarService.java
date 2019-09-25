package cn.tcmp.service.indexCar;

import cn.tcmp.entity.CarPictures;
import cn.tcmp.entity.Personnel;
import cn.tcmp.entity.Vehicle;
import cn.tcmp.util.PageUtils;
import cn.tcmp.vo.CarTestingVO;
import cn.tcmp.vo.CarVO;
import cn.tcmp.vo.ChaXunVO;
import cn.tcmp.vo.IndexVO;
import cn.tcmp.vo.jiance.CT1VO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/21
 */
public interface IndexCarService {

    //首页专用查询汽车
    List<IndexVO> queryCar(IndexVO indexVO);
    //分页查询所有汽车
    PageUtils<IndexVO> queryAllCar(ChaXunVO chaXunVO,Integer pageNum,Integer pageSize);
    //根据品牌查车系
    List<Vehicle> queryVehicleByPinPai(Integer id);
    //查询汽车信息
    CarTestingVO detailCar(Integer id);
    //查询车图片
    List<CarPictures> queryDetailImg(Integer id);
    //查询车检测
    List<CT1VO> queryJianCe(Integer id);
    //查看交易顾问旗下车辆
    PageUtils<IndexVO> queryGuWenCar(Integer id,Integer pageNum,Integer pageSize);
    //查看顾问
    Personnel queryPersonnelDetail(Integer id);
    //查看收藏
    List<IndexVO> queryCollection(Integer id);
    //查看是否已收藏
    IndexVO queryIsNoCollection(Integer userID,Integer carID);
    //收藏汽车
    int userCollection(Integer carID,Integer userID);
    //取消收藏
    int deleteCollection(Integer cID);

}
