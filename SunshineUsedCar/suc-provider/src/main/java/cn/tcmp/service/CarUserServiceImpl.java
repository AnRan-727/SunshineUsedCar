package cn.tcmp.service;

import cn.tcmp.dao.CarUserMapper;
import cn.tcmp.entity.CarUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

import javax.annotation.Resource;


/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/9
 */
@Service
public class CarUserServiceImpl implements CarUserService {

    @Resource
    private CarUserMapper carUserMapper;

    //用户登录
    @Override
    public CarUser carUserLogin(CarUser carUser) {
        //进行登录操作并返回
        return carUserMapper.carUserLogin(this.md5Encryption(carUser));
    }

    //判断手机号是否存在
    @Override
    public CarUser existCarUser(String userPhone) {
        return carUserMapper.existCarUser(userPhone);
    }
    //注册用户
    @Override
    public int carUserRegister(CarUser carUser) {
        return carUserMapper.carUserRegister(this.md5Encryption(carUser));
    }

    //查看用户详情信息
    @Override
    public CarUser detailCarUser(Integer id) {
        return carUserMapper.detailCarUser(id);
    }

    //修改用户信息
    @Override
    public int updateCarUser(CarUser carUser) {
        return carUserMapper.updateCarUser(this.md5Encryption(carUser));
    }

    //根据手机号或邮箱修改密码
    @Override
    public Integer updateUserPhone(CarUser carUser) {

        return carUserMapper.updateUserPhone(this.md5Encryption(carUser));
    }


    //MD5加密密码
    private CarUser md5Encryption(CarUser carUser) {
        //1.密码MD5加密并从第3位开始截取六位
        //获取输入的未加密的密码
        String userPassword = carUser.getUserPassWord();
        //MD5加密后的新密码
        String newPassword = DigestUtils.md5Hex(userPassword);
        //截取密码
        newPassword = newPassword.substring(2,8);
        //2.放入CarUser实体中
        carUser.setUserPassWord(newPassword);
        return carUser;
    }
}
