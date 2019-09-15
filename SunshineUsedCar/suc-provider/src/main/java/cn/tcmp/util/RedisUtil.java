package cn.tcmp.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    //redis保存
    public void set(String key,Object value,Integer timeout){
        ValueOperations<String,Object> vo=redisTemplate.opsForValue();
        vo.set(key,value);
        redisTemplate.expire(key,timeout,TimeUnit.SECONDS);
    }
    //redis获取
    public Object get(String key){
        ValueOperations<String,Object> vo=redisTemplate.opsForValue();
        return vo.get(key);
    }
    //redis删除
    public void delete(String key){
        redisTemplate.delete(key);
    }
}
