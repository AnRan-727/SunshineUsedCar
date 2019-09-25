package cn.tcmp.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/23
 */
@Data
public class ChaXunVO implements Serializable {


    private Integer carId,//汽车id
                    pinpaiId,//品牌id
                    chexiId,//车系id
                    cheling,//车龄
                    chexingId,//车型id
                    zuoweiId,//座位id
                    licheng,//里程数
                    biansuxiangId,//变速箱id
                    pailiang,//排量
                    paifangId,//排放标准id
                    ranyouId,//燃油id
                    yanseId,//颜色id
                    guobieId;//国别id
    //最高价 最低价
    private String zuigaojiage,zuidijiage;
    //品牌名称
    private String pinpaiName;
    private Integer superValue;//是否超值
    private Integer urgentSale;//是否急售
    private Integer newCar;//是否准新车
    private Integer transitive;//是否可迁全国
    private Integer paixu;//排序
    /*
    * 2.最新发布
    * 3.价格升序
    * 4.价格降序
    * 5.车龄升序
    * 6.车龄降序
    * 7.里程升序
    * 8.里程降序
    * */

}
