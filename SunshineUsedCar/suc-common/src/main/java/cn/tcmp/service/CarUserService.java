package cn.tcmp.service;

import cn.tcmp.entity.CarUser;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/9
 */
//客户登录已经个人信息
public interface CarUserService {

    //客户登录
    CarUser carUserLogin(CarUser carUser);

    //用户是否存在
    CarUser existCarUser(String userPhone);

    //客户注册
    int carUserRegister(CarUser carUser);

    //客户个人信息
    CarUser detailCarUser(Integer id);

    //修改个人信息
    int updateCarUser(CarUser carUser);

}
