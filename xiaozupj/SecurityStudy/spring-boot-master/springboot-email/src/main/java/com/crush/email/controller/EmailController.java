package com.crush.email.controller;

import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.Optional;


/**
 * @author crush
 */
@RestController
public class EmailController {

    final JavaMailSender javaMailSender;

    public EmailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    /**
     * 发送简单邮件
     *
     * @param email
     * @return
     */
    @GetMapping("/send/{email}")
    public String send(@PathVariable("email") String email) {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom("790933839@qq.com");
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似message.setTo("10*****16@qq.com","12****32*qq.com")
        message.setTo(email);
        // 设置邮件主题
        message.setSubject("大哥,我错了");
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        message.setText("祝你身体健康！万事胜意！！！");
        javaMailSender.send(message);
        return "OK";
    }



    /**
     * 发送复杂邮件
     *
     * @param email
     * @return
     */
    @GetMapping("/send2/{email}")
    public String send2(@PathVariable("email") String email) {
        //一个复杂的邮件
        MimeMessage message = this.javaMailSender.createMimeMessage();
        try {
            //组装
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //正文
            //主题
            helper.setSubject("小可爱，你好呀！");
            //开启html模式
            helper.setText("小可爱,我喜欢你丫", true);
            //附件
            helper.addAttachment("1.jpg", new File("C:\\Users\\ASUS\\Desktop\\杂七杂八\\杂图\\2.gif"));
            helper.setTo(email);
            helper.setFrom("790933839@qq.com");
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "OK";
    }
}