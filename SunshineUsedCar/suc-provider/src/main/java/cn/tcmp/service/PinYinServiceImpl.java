package cn.tcmp.service;

import cn.tcmp.entity.Vehicle;
import cn.tcmp.service.pinyin.PinYinService;
import com.alibaba.dubbo.config.annotation.Service;
import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/20
 */
@Service
public class PinYinServiceImpl implements PinYinService {

    private static Map<String,List<Vehicle>> map = new HashMap<>();

    @Override
    public String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);

            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert.substring(0,1).toUpperCase();
    }

    @Override
    public Map<String, List<Vehicle>> getMap(List<Vehicle> list) {
        map.clear();
        for (Vehicle str : list) {
            if(map.containsKey(getPinYinHeadChar(str.getVehicleName()))) {
                List<Vehicle> list2 = map.get(getPinYinHeadChar(str.getVehicleName()));
                list2.add(str);
            }else {
                List<Vehicle> strlist = new ArrayList<>();
                strlist.add(str);
                map.put(getPinYinHeadChar(str.getVehicleName()).toUpperCase(), strlist);
            }
        }
        return map;
    }
}
