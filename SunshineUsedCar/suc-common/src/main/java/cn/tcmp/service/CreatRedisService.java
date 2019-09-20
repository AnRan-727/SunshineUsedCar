package cn.tcmp.service;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/19
 */
//储存redis
public interface CreatRedisService {
    //redis保存
    void set(String key,Object value,Integer timeout);
    //redis获取
    Object get(String key);
    //redis删除
    void delete(String key);
}
