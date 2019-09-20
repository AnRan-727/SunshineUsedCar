package cn.tcmp.service;

import cn.tcmp.dao.CarQicheMapper;
import cn.tcmp.entity.Car;
import cn.tcmp.entity.Vehicle;
import cn.tcmp.util.PageQiche;
import cn.tcmp.vo.CarVO;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class CarQicheServiceImpl implements CarQicheService {
    @Autowired
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
            pageSize = 8;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<CarVO> carVOS = mapper.queryPageCarVo(car);
        PageInfo<CarVO> pageInfo = new PageInfo<>(carVOS);
        PageQiche<CarVO> pageQiche = new PageQiche<>();
        pageQiche.setTotal(pageInfo.getTotal());
        pageQiche.setList(pageInfo.getList());
        pageQiche.setNavigtepageNumbers(pageInfo.getNavigatepageNums());
        pageQiche.setPageNum(pageInfo.getPageNum());
        pageQiche.setPages(pageInfo.getPages());
        pageQiche.setPageSize(pageInfo.getPageSize());
        return pageQiche;
    }

    @Override
    public List<Vehicle> queryVehicleByVehicleName(Integer vehicleID) {
        return mapper.queryVehicleByVehicleName(vehicleID);
    }

    @Override
    public List<Vehicle> queryAllVehiclebyChexi() {
        return mapper.queryAllVehiclebyChexi();
    }

    @Override
    public PageQiche<CarVO> qeuryCarByVehicleChexiname(String vehicleName, Integer pageNum, Integer pageSize) {
        if(null == pageNum){
            pageNum = 1;
        }
        if(null == pageSize){
            pageSize = 8;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<CarVO> carVOS = mapper.qeuryCarByVehicleChexiname(vehicleName);
        PageInfo<CarVO> pageInfo = new PageInfo<>(carVOS);
        PageQiche<CarVO> pageQiche = new PageQiche<>();
        pageQiche.setTotal(pageInfo.getTotal());
        pageQiche.setList(pageInfo.getList());
        pageQiche.setNavigtepageNumbers(pageInfo.getNavigatepageNums());
        pageQiche.setPageNum(pageInfo.getPageNum());
        pageQiche.setPages(pageInfo.getPages());
        pageQiche.setPageSize(pageInfo.getPageSize());
        return pageQiche;
    }


    @Override
    public List<Vehicle> queryVehicleByVehicleName2(String vehicleName) {
        List<Vehicle> vehicles = mapper.queryVehicleByVehicleName2(vehicleName);
        return vehicles;
    }

    @Override
    public Vehicle detailVehicleById(Integer vehicleId) {
        return mapper.detailVehicleById(vehicleId);
    }


}
