package com.crush.exception.service;

import com.crush.exception.entity.LogEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Async("taskExecutor")
    @Override
    public void senderEmail(LogEmail logEmail) {
        log.info(Thread.currentThread().getName());
        //一个复杂的邮件
        MimeMessage message = this.javaMailSender.createMimeMessage();
        try {
            //组装
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //正文
            //主题
            helper.setSubject(logEmail.getSubject());
            //开启html模式
            helper.setText("<h1>"+logEmail.getContext()+"</h1>" ,true);
            //附件
            helper.addAttachment("1.jpg", new File("C:\\Users\\ASUS\\Desktop\\杂七杂八\\杂图\\2.gif"));
            helper.setTo(logEmail.getToEmail());
            helper.setFrom(fromEmail);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
