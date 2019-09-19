package cn.tcmp.service;

import cn.tcmp.entity.Car;
import cn.tcmp.entity.Vehicle;
import cn.tcmp.util.PageQiche;
import cn.tcmp.vo.CarVO;

import java.util.List;

public interface CarQicheService {

    //查询所有的车辆品牌
    List<Vehicle> queryAllVehicle();
    //根据汽车的品牌,车系,价格,车型等模糊查询汽车信息
    PageQiche<CarVO> queryPageCarVo(Car car,Integer pageNum,Integer pageSize);
    //根据品牌查询该车辆下属车系
    List<Vehicle> queryVehicleByVehicleName(Integer vehicleID);

    //查询全部车系
    List<Vehicle> queryAllVehiclebyChexi();
    //查询根据车辆品牌查询车辆信息
    List<CarVO> qeuryCarByVehicleChexiname(String  vehicleName);
    //模糊查询车辆票品牌
    List<Vehicle> queryVehicleByVehicleName2(String vehicleName);
    //根据品牌id查询品牌名称
    Vehicle detailVehicleById(Integer vehicleId);
    /*
    //按照分页查询全部汽车信息
    //查询全部的汽车信息
    //根据各种信息查询某种类型的汽车
    //根据各种信息模糊查询
    //根据下拉框查询汽车
    //查询所有汽车(按上牌时间升序)
    //查询所有汽车(按里程数升序)
    //查询所有汽车(按价格升序)
    //查询所有汽车(按车龄升序)
    //查询所有汽车(按Id升序——最新发布)
    //查询所有汽车(根据超值、急售、准新车、可迁全国查询)*/

}
