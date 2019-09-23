package cn.tcmp.service.indexCar;

import cn.tcmp.dao.IndexCarMapper;
import cn.tcmp.entity.CarPictures;
import cn.tcmp.vo.CarTestingVO;
import cn.tcmp.vo.CarVO;
import cn.tcmp.vo.IndexVO;
import cn.tcmp.vo.jiance.CT1VO;
import com.alibaba.dubbo.config.annotation.Service;
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
}
