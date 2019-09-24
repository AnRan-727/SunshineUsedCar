package cn.tcmp.service;

import cn.tcmp.dao.XiaLaKuangMapper;
import cn.tcmp.entity.DataDictionary;
import cn.tcmp.entity.Personnel;
import cn.tcmp.entity.Vehicle;
import cn.tcmp.service.xialakuang.XialakuangService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class XiaLaKuangServiceImpl implements XialakuangService {
    @Autowired
    private XiaLaKuangMapper mapper;


    @Override
    public List<DataDictionary> queryAllDataDictionary(String typeCode) {
        return mapper.queryAllDataDictionary(typeCode);
    }

    @Override
    public List<Vehicle> queryAllVehicle2() {
        return mapper.queryAllVehicle2();
    }

    @Override
    public List<Vehicle> queryAllCheXi() {
        return mapper.queryAllCheXi();
    }

    @Override
    public List<Personnel> queryAllYuanGong() {
        return mapper.queryAllYuanGong();
    }

    @Override
    public List<DataDictionary> queryAllZuoWei() {
        return mapper.queryAllZuoWei();
    }

    @Override
    public List<DataDictionary> queryAllBianShuXiang() {
        return mapper.queryAllBianShuXiang();
    }

    @Override
    public List<DataDictionary> queryAllPaiFang() {
        return mapper.queryAllPaiFang();
    }

    @Override
    public List<DataDictionary> queryAllRanYou() {
        return mapper.queryAllRanYou();
    }

    @Override
    public List<DataDictionary> queryAllYanSe() {
        return mapper.queryAllYanSe();
    }

    @Override
    public List<DataDictionary> queryAllGuoBie() {
        return mapper.queryAllGuoBie();
    }
}
