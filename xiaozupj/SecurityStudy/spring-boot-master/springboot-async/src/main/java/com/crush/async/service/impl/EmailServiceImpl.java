package com.crush.async.service.impl;

import com.crush.async.entity.Account;
import com.crush.async.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author: crush
 * @Date: 2021-07-29 16:39
 * version 1.0
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private  JavaMailSender javaMailSender;


    @Async("taskExecutor")
    @Override
    public void senderEmail(Account account) {
        log.info(Thread.currentThread().getName());
        //一个复杂的邮件
        MimeMessage message = this.javaMailSender.createMimeMessage();
        try {
            //组装
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //正文
            //主题
            helper.setSubject("小可爱，欢迎你的到来哦！！！");
            //开启html模式
            helper.setText("<h1>小可爱,我想你一定会喜欢这里吧！！！</h1>" +
                    "<p>你的账号为:"+account.getUsername()+"</p>" +
                    "<p>你的密码为："+account.getPassword()+"</p>", true);
            //附件
            helper.addAttachment("1.jpg", new File("C:\\Users\\ASUS\\Desktop\\杂七杂八\\杂图\\2.gif"));
            helper.setTo(account.getEmail());
            helper.setFrom("790933839@qq.com");
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
