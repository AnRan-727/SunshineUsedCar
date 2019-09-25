package cn.tcmp.service.indexCar;

import cn.tcmp.dao.IndexCarMapper;
import cn.tcmp.entity.CarPictures;
import cn.tcmp.entity.Personnel;
import cn.tcmp.entity.Vehicle;
import cn.tcmp.util.PageUtils;
import cn.tcmp.vo.CarTestingVO;
import cn.tcmp.vo.ChaXunVO;
import cn.tcmp.vo.IndexVO;
import cn.tcmp.vo.jiance.CT1VO;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/21
 */
@Service
public class IndexCarServiceImpl implements IndexCarService {

    @Autowired
    private IndexCarMapper indexCarMapper;

    @Override
    public List<IndexVO> queryCar(IndexVO indexVO) {
        return indexCarMapper.queryCar(indexVO);
    }

    @Override
    public PageUtils<IndexVO> queryAllCar(ChaXunVO chaXunVO, Integer pageNum, Integer pageSize) {
        if(pageNum == null){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 12;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<IndexVO> list = indexCarMapper.queryAllCar(chaXunVO);
        PageInfo<IndexVO> pageInfo = new PageInfo<>(list);
        PageUtils<IndexVO> pageUtils = new PageUtils<>(pageInfo);
        return pageUtils;
    }

    @Override
    public List<Vehicle> queryVehicleByPinPai(Integer id) {
        return indexCarMapper.queryVehicleByPinPai(id);
    }

    @Override
    public CarTestingVO detailCar(Integer id) {
        return indexCarMapper.detailCar(id);
    }

    @Override
    public List<CarPictures> queryDetailImg(Integer id) {
        return indexCarMapper.queryDetailImg(id);
    }

    @Override
    public List<CT1VO> queryJianCe(Integer id) {
        return indexCarMapper.queryJianCe(id);
    }

    @Override
    public PageUtils<IndexVO> queryGuWenCar(Integer id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<IndexVO> indexVOS = indexCarMapper.queryGuWenCar(id);
        PageInfo<IndexVO> pageInfo = new PageInfo<>(indexVOS);
        PageUtils<IndexVO> pageUtils = new PageUtils<>(pageInfo);
        return pageUtils;
    }

    @Override
    public Personnel queryPersonnelDetail(Integer id) {
        return indexCarMapper.queryPersonnelDetail(id);
    }

    @Override
    public List<IndexVO> queryCollection(Integer id) {
        return indexCarMapper.queryCollection(id);
    }

    @Override
    public IndexVO queryIsNoCollection(Integer userID, Integer carID) {
        return indexCarMapper.queryIsNoCollection(userID,carID);
    }

    @Override
    public int userCollection(Integer carID, Integer userID) {
        return indexCarMapper.userCollection(carID,userID);
    }

    @Override
    public int deleteCollection(Integer cID) {
        return indexCarMapper.deleteCollection(cID);
    }
}
