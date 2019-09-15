package cn.tcmp.service;

import cn.tcmp.dao.PersonnelvMapper;
import cn.tcmp.entity.Personnel;
import com.alibaba.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Service
public class PersonnelvServiceImpl implements PersonnelvService{
    @Resource
    private PersonnelvMapper personnelvMapper;
    @Override
    public Personnel PersonnelLogin(Personnel personnel) {
        return personnelvMapper.PersonnelLogin(personnel);
    }

    @Override
    public int PersonnelUpdate(Personnel personnel) {
        return personnelvMapper.PersonnelUpdate(personnel);
    }

    @Override
    public int PersonnelAdd(Personnel personnel) {
        return personnelvMapper.PersonnelAdd(personnel);
    }

    @Override
    public int PersonnelDelete(Integer id) {
        return personnelvMapper.PersonnelDelete(id);
    }
}
