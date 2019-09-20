package cn.tcmp.service.browse;


import cn.tcmp.dao.BrowseMapper;
import cn.tcmp.entity.Browse;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/20
 */
@Service
public class BrowseServiceImpl implements BrowseService {

    @Autowired
    private BrowseMapper browseMapper;


    @Override
    public int addBrowse(Browse browse) {
        return browseMapper.addBrowse(browse);
    }
}
