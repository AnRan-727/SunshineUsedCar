package cn.tcmp.service.dataDictionary;

import cn.tcmp.dao.DataDictionaryMapper;
import cn.tcmp.entity.DataDictionary;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/20
 */
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Override
    public List<DataDictionary> querySingle(DataDictionary dataDictionary) {
        return dataDictionaryMapper.querySingle(dataDictionary);
    }
}
