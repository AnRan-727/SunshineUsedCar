package cn.tcmp.dao;

import cn.tcmp.entity.CarUser;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/9
 */
public interface CarUserMapper {
    //查询所有用户
    List<CarUser> carUserQuery(CarUser carUser);
    //删除用户
    int carUserDelete(Integer id);

    //客户登录
    CarUser carUserLogin(CarUser carUser);

    //用户是否存在
    CarUser existCarUser(String userPhone);

    //客户个人信息
    CarUser detailCarUser(Integer userID);

    //客户注册
    int carUserRegister(CarUser carUser);

    //修改个人信息
    int updateCarUser(CarUser carUser);
    //后台修改个人信息
    int updateCarUser2(CarUser carUser);

    //根据手机号或者邮箱修改密码
    Integer updateUserPhone(CarUser carUser);

}
