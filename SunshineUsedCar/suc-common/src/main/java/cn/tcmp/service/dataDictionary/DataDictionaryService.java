package cn.tcmp.service.dataDictionary;

import cn.tcmp.entity.DataDictionary;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/20
 */
public interface DataDictionaryService {

    //查询单个类型
    List<DataDictionary> querySingle(DataDictionary dataDictionary);


}
