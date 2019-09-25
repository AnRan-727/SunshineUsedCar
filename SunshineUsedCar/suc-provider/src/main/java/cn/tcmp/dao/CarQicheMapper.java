package cn.tcmp.dao;

import cn.tcmp.entity.Car;
import cn.tcmp.entity.Vehicle;
import cn.tcmp.util.PageQiche;
import cn.tcmp.vo.CarVO;
import cn.tcmp.vo.CarVO2;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CarQicheMapper {

    //查询所有的车辆品牌
    @Select(value = "SELECT * FROM vehicle WHERE parentID =-1 limit 16")
    List<Vehicle> queryAllVehicle();

    //根据品牌查询该车辆下属车系
    @Select(value ="SELECT * FROM vehicle WHERE parentID=(SELECT vehicleID FROM vehicle WHERE vehicleID=#{vehicleID})")
    List<Vehicle> queryVehicleByVehicleName(@Param("vehicleID") Integer vehicleID);

    //根据汽车的品牌,车系,价格,车型等模糊查询汽车信息
    List<CarVO> queryPageCarVo(Car car);

    //查询全系部车
    @Select(value = "select * from vehicle where parentID!=-1")
    List<Vehicle> queryAllVehiclebyChexi();

    //查询根据车辆品牌查询车辆信息
    List<CarVO> qeuryCarByVehicleChexiname(@Param("vehicleName") String  vehicleName);

    //模糊查询车辆票品牌
    @Select(value = "select * from vehicle where parentID=-1 and vehicleName like CONCAT('%',#{vehicleName},'%')")
    List<Vehicle> queryVehicleByVehicleName2(@Param("vehicleName") String vehicleName);

    //根据品牌id查询品牌名称
    @Select(value = "select vehicleName from vehicle where vehicleId=#{vehicleId}")
    Vehicle detailVehicleById(@Param("vehicleId") Integer vehicleId);

    //查询所有的汽车信息
    List<CarVO> queryAllQiche();

    //根据车辆id查询车辆信息
    CarVO2 detailCar(@Param("carID") Integer carID);
    //删除车辆信息
    @Delete(value = "delete from car where carID=#{carID}")
    Integer deleteCar(@Param("carID") Integer carID);
    //增加车辆信息
    Integer insertCar(Car car);
    //修改编辑车辆信息
    @Update(value = "update Car set pinpaiID=#{car.pinpaiID},vehicleID={car.vehicleID},vehicleAge={car.vehicleAge},mileage={car.mileage},model={car.model},presentPrice={car.presentPrice}" +
            ",originalPrice={car.originalPrice},typeID={car.typeID},seatID={car.seatID},gearboxID={car.gearboxID},displacement={car.displacement},emissionID={car.emissionID},fuelID={car.fuelID},colorID={car.colorID}" +
            ",countryID={car.countryID},topCity={car.topCity},placingTime={car.placingTime},yearlyTime={car.yearlyTime},insuranceTime={car.insuranceTime},superValue={car.superValue}" +
            ",urgentSale={car.urgentSale},newCar={car.newCar},transitive={car.transitive},personnelID={car.personnelID}," +
            ",subordinateName={car.subordinateName},shelfTime={car.shelfTime} where carID=#{carID}")
    Integer updateCar(@Param("car") Car car);



}
