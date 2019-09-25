package cn.tcmp.service.xialakuang;

import cn.tcmp.entity.DataDictionary;
import cn.tcmp.entity.Personnel;
import cn.tcmp.entity.Vehicle;

import java.util.List;

public interface XialakuangService {

//    查询所有字典
        List<DataDictionary> queryAllDataDictionary(String typeCode);
//     查询所有的品牌
        List<Vehicle> queryAllVehicle2();
        //     查询所有的车系
        List<Vehicle> queryAllCheXi();
        //     查询所有的员工
        List<Personnel> queryAllYuanGong();
//    查询所有座位
        List<DataDictionary> queryAllZuoWei();
//    查询所有变速箱
        List<DataDictionary> queryAllBianShuXiang();
//    查询所有排放标准
        List<DataDictionary> queryAllPaiFang();
//    查询所有燃油
        List<DataDictionary> queryAllRanYou();
//    查询所有颜色
        List<DataDictionary> queryAllYanSe();
//    查询所有国别
        List<DataDictionary> queryAllGuoBie();
}
