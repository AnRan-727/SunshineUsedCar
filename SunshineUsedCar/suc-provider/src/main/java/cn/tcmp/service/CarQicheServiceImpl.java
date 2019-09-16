package cn.tcmp.service;

import cn.tcmp.dao.CarQicheMapper;
import cn.tcmp.entity.Car;
import cn.tcmp.entity.Vehicle;
import cn.tcmp.util.PageQiche;
import cn.tcmp.vo.CarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public List<Vehicle> queryVehicleByVehicleName(Integer vehicleID) {
        return mapper.queryVehicleByVehicleName(vehicleID);
    }
}
