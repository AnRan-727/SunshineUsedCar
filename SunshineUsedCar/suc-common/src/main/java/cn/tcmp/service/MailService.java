package cn.tcmp.service;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/19
 */
public interface MailService {
    //发送邮件
    void sendHtmlMail(String to,String subject,String content);
}
