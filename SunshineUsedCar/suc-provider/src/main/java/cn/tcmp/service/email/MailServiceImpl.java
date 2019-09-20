package cn.tcmp.service.email;

import cn.tcmp.service.MailService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/19
 */
@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendHtmlMail(String to, String subject, String content) {

        //消息处理类
        MimeMessage mimeMailMessage=null;
        try{
            mimeMailMessage=javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper
                    =new MimeMessageHelper(mimeMailMessage);
            //设置发件人
            mimeMessageHelper.setFrom(from);
            //设置收件人
            mimeMessageHelper.setTo(to);
            //设置邮件标题
            mimeMessageHelper.setSubject(subject);
            //设置邮件内容
            mimeMessageHelper.setText(content);
            javaMailSender.send(mimeMailMessage);
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
