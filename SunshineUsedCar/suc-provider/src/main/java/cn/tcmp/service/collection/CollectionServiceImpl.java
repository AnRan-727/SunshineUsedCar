package cn.tcmp.service.collection;

import cn.tcmp.dao.CollectionMapper;
import cn.tcmp.entity.Collection;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/20
 */
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;


    @Override
    public int addCollection(Collection collection) {
        return collectionMapper.addCollection(collection);
    }

    @Override
    public int deleteCollection(Integer id) {
        return collectionMapper.deleteCollection(id);
    }
}
