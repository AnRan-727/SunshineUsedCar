package cn.tcmp.vo;
import cn.tcmp.entity.CarPictures;
import cn.tcmp.entity.Personnel;
import cn.tcmp.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * 汽车vo类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarVO2 implements Serializable {
    private Integer carID;//汽车id
    private String pinpaiID;//品牌id
    private String vehicleIDsss;//车类id
    private Integer vehicleAge;//车龄
    private String mileage;//行驶里程
    private String model;//型号
    private String presentPrice;//现价
    private String originalPrice;//原价
    private String typeID;//车型id
    private String seatID;//座位数id
    private String gearboxID;//变速箱id
    private Integer displacement;//排量
    private String emissionID;//排放标准id
    private String fuelID;//烟油id
    private String colorID;//颜色id
    private String countryID;//国别id
    private String topCity;//上牌城市
    private Date placingTime;//上牌时间
    private Date yearlyTime;//年检到期时间
    private Date insuranceTime;//保险到期时间
    private Integer superValue;//是否超值
    private Integer urgentSale;//是否急售
    private Integer newCar;//是否准新车
    private Integer transitive;//是否可迁全国
    private Personnel personnel;//工作人员
    private String subordinateName;//车主
    private Vehicle vehicle;
    private Date shelfTime;//上架时间
    private Integer zuiXinShangJia;//最新上架>>>首页排序专用
    private CarPictures carPictures;//汽车图片


}
