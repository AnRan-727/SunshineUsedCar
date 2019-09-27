package cn.tcmp.dao;

import cn.tcmp.entity.Detection;

import java.util.List;

public interface DetectionMapper {
    //根据汽车品牌查询检测项
    List<Detection> DetectionList(Integer vid);
}
