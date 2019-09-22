package cn.tcmp.dao;

import cn.tcmp.entity.CarPictures;
import cn.tcmp.vo.CarTestingVO;
import cn.tcmp.vo.CarVO;
import cn.tcmp.vo.IndexVO;
import cn.tcmp.vo.jiance.CT1VO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/21
 */
public interface IndexCarMapper {
    //首页专用查询汽车
    List<IndexVO> queryCar(IndexVO indexVO);

    //查询汽车详情
    CarTestingVO detailCar(Integer id);
    //查询车图片
    List<CarPictures> queryDetailImg(Integer id);
    //查询车检测
    List<CT1VO> queryJianCe(Integer id);
}
