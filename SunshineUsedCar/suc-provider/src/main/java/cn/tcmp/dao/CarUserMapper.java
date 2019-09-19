package cn.tcmp.dao;

import cn.tcmp.entity.CarUser;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/9
 */
public interface CarUserMapper {

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
}
