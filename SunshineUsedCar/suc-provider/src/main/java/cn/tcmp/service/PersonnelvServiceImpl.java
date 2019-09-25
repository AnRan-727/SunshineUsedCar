package cn.tcmp.service;

import cn.tcmp.dao.PersonnelvMapper;
import cn.tcmp.entity.Personnel;
import cn.tcmp.util.PageUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;

//import com.alibaba.dubbo.container.page.PageHandler;

@Service
public class PersonnelvServiceImpl implements PersonnelvService{
    @Resource
    private PersonnelvMapper personnelvMapper;

    @Override
    public PageUtils<Personnel> personnelList(Integer pageNumber, Integer pageSize, Personnel personnel) {
        if (pageNumber==null){
            pageNumber=1;
        }
        if (pageSize==null){
            pageSize=7;
        }
        PageHelper.startPage(pageNumber,pageSize);
        List<Personnel> list=this.personnelvMapper.personnelList(personnel);
        PageInfo<Personnel> pageinfo=new PageInfo<>(list);
        PageUtils<Personnel> pageUtils=new PageUtils<>(pageinfo);
        pageUtils.setNavigatepageNums(pageinfo.getNavigatepageNums());
        return pageUtils;
    }

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
