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
    private String mileage,model,presentPrice,displacement,originalPrice,topCity,carPicturesURL;
    private String shelfTime,placingTime,personnelDate;//上架时间 上牌时间 入职时间
    private Integer superValue;//是否超值
    private Integer urgentSale;//是否急售
    private Integer newCar;//是否准新车
    private Integer zuiXinShangJia;//最新上架>>>首页排序专用
    private Integer collectionID;//收藏ID
    private Integer personneID;//工作人员ID
    private String personnelRealName,personnelPhone;//工作人员名字,手机号
    private String ddt,ddt2,ddt3,ddt4,ddt5,ddt6,chexi,pinpai;



}
