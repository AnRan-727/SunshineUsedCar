package cn.tcmp.dao;

import cn.tcmp.entity.Car;
import cn.tcmp.entity.Vehicle;
import cn.tcmp.util.PageQiche;
import cn.tcmp.vo.CarVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CarQicheMapper {

    //查询所有的车辆品牌
    @Select(value = "SELECT * FROM vehicle WHERE parentID =-1 limit 10")
    List<Vehicle> queryAllVehicle();

    //根据品牌查询该车辆下属车系
    @Select(value ="SELECT * FROM vehicle WHERE parentID=(SELECT vehicleID FROM vehicle WHERE vehicleID=#{vehicleID})")
    List<Vehicle> queryVehicleByVehicleName(@Param("vehicleID") Integer vehicleID);

    //根据汽车的品牌,车系,价格,车型等模糊查询汽车信息
    List<CarVO> queryPageCarVo(Car car);

}
