package cn.tcmp.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Detection implements Serializable {
    private int detectionid;//监测归属id
    private Vehicle vID;//监测id
    private Testing tid;//品牌id
}
