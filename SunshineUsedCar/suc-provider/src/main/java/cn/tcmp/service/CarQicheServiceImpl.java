package cn.tcmp.service;

import cn.tcmp.dao.CarQicheMapper;
import cn.tcmp.entity.Car;
import cn.tcmp.entity.Vehicle;
import cn.tcmp.util.PageQiche;
import cn.tcmp.vo.CarVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CarQicheServiceImpl implements CarQicheService {
    @Resource
    private CarQicheMapper mapper;
    @Override
    public List<Vehicle> queryAllVehicle() {
        return mapper.queryAllVehicle();
    }

    @Override
    public PageQiche<CarVO> queryPageCarVo(Car car, Integer pageNum, Integer pageSize) {
        if(null == pageNum){
            pageNum = 1;
        }
        if(null == pageSize){
            pageSize = 6;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<CarVO> carVOS = mapper.queryPageCarVo(car);
        PageInfo<CarVO> pageInfo = new PageInfo<>(carVOS);
        PageQiche<CarVO> pageQiche = new PageQiche<>();
        pageQiche.setListF(pageInfo.getList());
        pageQiche.setNavigtepageNumbers(pageInfo.getNavigatepageNums());
        pageQiche.setPageNumF(pageInfo.getPageNum());
        pageQiche.setPagesF(pageInfo.getPages());
        pageQiche.setPageSizeF(pageInfo.getPageSize());
        return pageQiche;
    }

    @Override
    public List<Vehicle> queryVehicleByVehicleName(Integer vehicleID) {
        return mapper.queryVehicleByVehicleName(vehicleID);
    }
}
