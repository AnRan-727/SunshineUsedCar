package cn.tcmp.service;

import cn.tcmp.entity.Personnel;
import cn.tcmp.util.PageUtils;

public interface PersonnelvService {
    //查询所有工作人员
    PageUtils<Personnel> personnelList(Integer pageNumber,Integer pageSize,Personnel personnel);
    //工作人员登录
    Personnel PersonnelLogin(Personnel personnel);
    //工作人员修改
    int PersonnelUpdate(Personnel personnel);
    //工作人员添加
    int PersonnelAdd(Personnel personnel);
    //工作人员删除
    int PersonnelDelete(Integer id);
}
