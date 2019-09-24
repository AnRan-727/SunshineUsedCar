package cn.tcmp.dao;

import cn.tcmp.entity.DataDictionary;
import cn.tcmp.entity.Personnel;
import cn.tcmp.entity.Vehicle;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface XiaLaKuangMapper {

    //     查询所有的品牌
    @Select(value = "select * from vehicle where parentID=-1")
    List<Vehicle> queryAllVehicle2();
    //    查询所有车型
    @Select(value = "SELECT * FROM datadictionary WHERE typeCode=#{typeCode}")
    List<DataDictionary> queryAllDataDictionary(@Param("typeCode") String typeCode);
    //    查询所有座位
    @Select(value = "SELECT * FROM datadictionary WHERE typeCode='SEAT'")
    List<DataDictionary> queryAllZuoWei();
    //    查询所有变速箱
    @Select(value = "SELECT * FROM datadictionary WHERE typeCode='GEARBOX'")
    List<DataDictionary> queryAllBianShuXiang();
    //    查询所有排放标准
    @Select(value = "SELECT * FROM datadictionary WHERE typeCode='EMISSION'")
    List<DataDictionary> queryAllPaiFang();
    //    查询所有燃油
    @Select(value = "SELECT * FROM datadictionary WHERE typeCode='FUEL'")
    List<DataDictionary> queryAllRanYou();
    //    查询所有颜色
    @Select(value = "SELECT * FROM datadictionary WHERE typeCode='COLOR'")
    List<DataDictionary> queryAllYanSe();
    //    查询所有国别
    @Select(value = "SELECT * FROM datadictionary WHERE typeCode='COUNTRY'")
    List<DataDictionary> queryAllGuoBie();
    //     查询所有的车系
    @Select(value = "select * from vehicle where parentID!=-1")
    List<Vehicle> queryAllCheXi();
    //     查询所有的车系
    @Select(value = "select * from personnel")
    List<Personnel> queryAllYuanGong();
}
