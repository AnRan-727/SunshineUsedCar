package cn.tcmp.service;

import cn.tcmp.dto.TokenDto;
import cn.tcmp.entity.CarUser;

/**
 * Created by Administrator on 2019/8/26/026.
 */
public interface TokenService {

    //生成Token
    String createToken(String userAgent, CarUser user);
    //保存Token
    Integer saveToken(String token, CarUser user);
    //验证Token
    Boolean checkToken(String token, String userAgent);

    //退出
    TokenDto logout(String token, String userAgent);

    //重置Token
    TokenDto reset(String token, String userAgent);
}
