package cn.tcmp.dao;

import cn.tcmp.entity.Personnel;

public interface PersonnelvMapper {
    //工作人员登录
    Personnel PersonnelLogin(Personnel personnel);
    //工作人员修改
    int PersonnelUpdate(Personnel personnel);
    //工作人员添加
    int PersonnelAdd(Personnel personnel);
    //工作人员删除
    int PersonnelDelete(Integer id);
}
