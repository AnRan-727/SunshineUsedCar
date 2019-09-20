package cn.tcmp.dao;

import cn.tcmp.entity.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/20
 */
public interface CollectionMapper {
    //收藏
    int addCollection(Collection collection);
    //删除收藏
    int deleteCollection(Integer id);
}
