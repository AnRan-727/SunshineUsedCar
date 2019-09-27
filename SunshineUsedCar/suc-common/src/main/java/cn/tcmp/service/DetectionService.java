package cn.tcmp.service;

import cn.tcmp.entity.Detection;

import java.util.List;

public interface DetectionService {
    //根据汽车品牌查询检测项
    List<Detection> DetectionList(Integer vid);
}
