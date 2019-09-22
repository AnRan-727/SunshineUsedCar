package cn.tcmp.service;

import cn.tcmp.dto.DataDto;
import cn.tcmp.dto.TokenDto;
import cn.tcmp.entity.Personnel;
import cn.tcmp.util.Common;
import cn.tcmp.util.RedisUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class HouTaiTokenServiceImpl implements HouTaiTokenService{
    @Resource
    private RedisUtil redisUtil;
    //生成token
    @Override
    public String createToken(String userAgent, Personnel user) {
        StringBuffer strB = new StringBuffer();
        strB.append(Common.TOKEN_PREFIX);
        //判断客户端特征
        UserAgent agent=UserAgent.parseUserAgentString(userAgent);
        if (agent.getOperatingSystem().isMobileDevice()) {
            strB.append(Common.MOBILE+"-");
        }else {
            strB.append(Common.PC+"-");
        }
        //userCode MD5加密
        String userCode=user.getPersonnelPhone();
        strB.append(DigestUtils.md5Hex(userCode)+"-");
        //userId
        strB.append(user.getPersonnelID()+"-");
        //创建时间
        Date now = new Date();
        DateFormat format=new SimpleDateFormat("yyyyMMddHHmmsss");
        String dataStr="";
        dataStr = format.format(now);
        strB.append(dataStr+"-");
        //agent MD5 截取前6位
        String agentMD5=DigestUtils.md5Hex(userAgent);
        agentMD5=agentMD5.substring(0,6);
        strB.append(agentMD5);
        return strB.toString();
    }
    //保存token
    @Override
    public Integer saveToken(String token, Personnel user) {
        //user->json
        String json = JSON.toJSONString(user);
        redisUtil.set(token,json,Common.TOKEN_TIMEOUT);
        return 1;
    }
    //验证token
    @Override
    public Boolean checkToken(String token, String userAgent) {
        //1. 通过入参token查询Redis中是否有符合的记录
        Object userJson = redisUtil.get(token);
        if (null == userJson) {
            return false;
        }
        //2. 验证userAgent的信息是否与登录时生成的一致
        String oldAgent=token.split("-")[4];
        String newAgent=DigestUtils.md5Hex(userAgent).substring(0,6);
        if(oldAgent.equals(newAgent)){
            return true;
        }
        return false;
    }
    //注销token
    @Override
    public TokenDto logout(String token, String userAgent) {
        TokenDto tokenDto=new TokenDto();
        //验证
        Boolean result = checkToken(token, userAgent);
        if (!result) {
            tokenDto.setErrorcode(Common.CODE_03);
            return tokenDto;
        }
        redisUtil.delete(token);
        tokenDto.setErrorcode(Common.CODE_SUCCESS);
        return tokenDto;
    }
    //重置token
    @Override
    public TokenDto reset(String token, String userAgent) {
        TokenDto tokenDto=new TokenDto();

        // 验证该token是否到重置时间（>1.5小时）
        String tokenDateStr=token.split("-")[3];
        DateFormat format=new SimpleDateFormat("yyyyMMddHHmmsss");
        Date tokenDate=null;
        try {
            tokenDate= format.parse(tokenDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long nowTime= Calendar.getInstance().getTimeInMillis();
        Long tokenCreateTime=tokenDate.getTime();
        Long passTime=nowTime-tokenCreateTime;
        if(passTime<Common.TOKEN_RESET_TIME){
            //没有到重置时间
            tokenDto.setErrorcode(Common.CODE_04);
            return tokenDto;
        }
        //1. 验证token是否有效
        Boolean result = checkToken(token, userAgent);
        if (!result) {
            //token无效
            tokenDto.setErrorcode(Common.CODE_03);
            return tokenDto;
        }
        //2. 生成新的token
        String userJson = redisUtil.get(token)+"";
        Personnel user = JSON.parseObject(userJson, Personnel.class);
        String newToken = createToken(userAgent, user);
        //3. 将新的token保存到redis中
        Integer count = saveToken(newToken, user);
        //4. 将新的token封装到tokenDto中
        DataDto data=new DataDto();
        data.setToken(newToken);
        data.setGenTime(Calendar.getInstance().getTimeInMillis()+"");
        data.setExpTime(Calendar.getInstance().getTimeInMillis()
                +Common.TOKEN_TIMEOUT+"");
        tokenDto.setData(data);
        tokenDto.setErrorcode(Common.CODE_SUCCESS);
        //5. 老token延迟2分钟失效
        redisUtil.set(token,userJson,Common.TOKEN_LONG_TIME);
        return tokenDto;
    }
}
