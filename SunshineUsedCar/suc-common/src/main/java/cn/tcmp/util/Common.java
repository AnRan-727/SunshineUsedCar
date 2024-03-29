package cn.tcmp.util;

/**
 * Created by Administrator on 2019/8/26/026.
 */
public class Common {

    //错误编码
    public static  final String CODE_SUCCESS="0000";
    //Token 超时
    public static final String CODE_01="0001";
    //用户名密码错误
    public static final String CODE_02="0002";
    //token验证失败
    public static final String CODE_03="0003";
    //重置Token时间条件未达到
    public static final String CODE_04="0004";
    //token失效的时间
    public static final Integer TOKEN_TIMEOUT=1*30*60;
    //redis失效时间
    public static final Integer REDIS_TIMEOUT=2*60*1000;
    //token的重置时间
    public static final Integer TOKEN_RESET_TIME=90*60*1000;
    //老token延迟2分钟
    public static final Integer TOKEN_LONG_TIME=2*60;

    //token 前缀
    public static final String TOKEN_PREFIX="token:";

    //PC端
    public static final String PC="PC";
    //移动端
    public static final String MOBILE="MOBILE";
}
