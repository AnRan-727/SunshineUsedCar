package cn.tcmp.service.pinyin;

import cn.tcmp.entity.Vehicle;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/20
 */
public interface PinYinService {

    //字符串转字母   然后首字母大写
    String getPinYinHeadChar(String str);
    //遍历List集合  转字母并存进Map里
    Map<String,List<Vehicle>> getMap(List<Vehicle> list);


}
