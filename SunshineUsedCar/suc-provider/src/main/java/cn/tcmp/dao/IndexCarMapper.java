package cn.tcmp.dao;

import cn.tcmp.entity.CarPictures;
import cn.tcmp.entity.Personnel;
import cn.tcmp.entity.Vehicle;
import cn.tcmp.util.PageUtils;
import cn.tcmp.vo.CarTestingVO;
import cn.tcmp.vo.CarVO;
import cn.tcmp.vo.ChaXunVO;
import cn.tcmp.vo.IndexVO;
import cn.tcmp.vo.jiance.CT1VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/21
 */
public interface IndexCarMapper {
    //首页专用查询汽车
    List<IndexVO> queryCar(IndexVO indexVO);
    //分页查询所有汽车
    List<IndexVO> queryAllCar(ChaXunVO chaXunVO);
    //根据品牌查车系
    List<Vehicle> queryVehicleByPinPai(Integer id);
    //查询汽车详情
    CarTestingVO detailCar(Integer id);
    //查询车图片
    List<CarPictures> queryDetailImg(Integer id);
    //查询车检测
    List<CT1VO> queryJianCe(Integer id);
    //查看交易顾问旗下车辆
    List<IndexVO> queryGuWenCar(Integer id);
    //查看顾问
    Personnel queryPersonnelDetail(Integer id);
    //查看是否已收藏
    IndexVO queryIsNoCollection(@Param("userID") Integer userID,@Param("carID") Integer carID);
    //查看收藏
    List<IndexVO> queryCollection(Integer id);
    //收藏汽车
    int userCollection(@Param("carID") Integer carID,@Param("userID") Integer userID);
    //取消收藏
    int deleteCollection(Integer cID);


}
