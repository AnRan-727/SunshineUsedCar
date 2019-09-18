package cn.tcmp.dao;

import cn.tcmp.entity.Personnel;

import java.util.List;

public interface PersonnelvMapper {
    //查询所有工作人员
    List<Personnel> personnelList(Personnel personnel);
    //工作人员登录
    Personnel PersonnelLogin(Personnel personnel);
    //工作人员修改
    int PersonnelUpdate(Personnel personnel);
    //工作人员添加
    int PersonnelAdd(Personnel personnel);
    //工作人员删除
    int PersonnelDelete(Integer id);
}
