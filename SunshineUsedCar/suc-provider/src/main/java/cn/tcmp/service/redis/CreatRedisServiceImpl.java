package cn.tcmp.service.redis;

import cn.tcmp.service.CreatRedisService;
import cn.tcmp.util.RedisUtil;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/19
 */
@Service
public class CreatRedisServiceImpl implements CreatRedisService {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void set(String key,Object value,Integer timeout) {

        redisUtil.set(key,value,timeout);
    }

    @Override
    public Object get(String key) {

        return redisUtil.get(key);
    }

    @Override
    public void delete(String key) {
        redisUtil.delete(key);
    }
}
