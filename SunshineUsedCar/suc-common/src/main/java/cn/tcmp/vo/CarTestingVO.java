package cn.tcmp.vo;

import cn.tcmp.entity.CarPictures;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * 汽车详情页   汽车检测
 * Date: 2019/9/21
 */
@Data
public class CarTestingVO implements Serializable {

    private Integer carID;
    private String
            goodOrBad,
            model,
            presentPrice,
            topCity,
            placingTime,
            mileage,
            displacement,
            ddt,
            ddt2,
            ddt3,
            ddt4;
    private Date detectionTime;//检测时间

}
