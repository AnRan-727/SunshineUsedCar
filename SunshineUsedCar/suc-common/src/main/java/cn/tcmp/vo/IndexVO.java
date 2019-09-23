package cn.tcmp.vo;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/21
 */
@Data
public class IndexVO implements Serializable {

    private Integer carID;
    private String mileage,model,presentPrice,originalPrice,topCity,carPicturesURL;
    private Date placingTime;
    private Integer superValue;//是否超值
    private Integer urgentSale;//是否急售
    private Integer newCar;//是否准新车
    private Integer zuiXinShangJia;//最新上架>>>首页排序专用



}
