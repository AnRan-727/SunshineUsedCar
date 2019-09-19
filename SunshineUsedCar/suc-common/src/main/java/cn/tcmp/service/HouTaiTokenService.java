package cn.tcmp.service;

import cn.tcmp.dto.TokenDto;
import cn.tcmp.entity.Personnel;

/**
 * 后台登录
 */
public interface HouTaiTokenService {

    //生成Token
    String createToken(String userAgent, Personnel user);
    //保存Token
    Integer saveToken(String token, Personnel user);
    //验证Token
    Boolean checkToken(String token, String userAgent);

    //退出
    TokenDto logout(String token, String userAgent);

    //重置Token
    TokenDto reset(String token, String userAgent);
}
