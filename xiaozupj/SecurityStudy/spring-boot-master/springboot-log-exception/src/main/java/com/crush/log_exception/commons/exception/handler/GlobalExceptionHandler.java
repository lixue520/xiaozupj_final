package com.crush.log_exception.commons.exception.handler;

import com.crush.log_exception.commons.ResponseDto;
import com.crush.log_exception.entity.LogEmail;
import com.crush.log_exception.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author crush
 * @ControllerAdvice
 * @ResponseBody //表示返回的对象，Spring会自动把该对象进行json转化，最后写入到Response中。
 */
@ControllerAdvice
@ResponseBody
@Component
public class GlobalExceptionHandler {

    @Autowired
    EmailService emailService;
    /**
     * //表示让Spring捕获到所有抛出的SignException异常，并交由这个被注解的方法处理。
     * //表示设置状态码
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    ResponseDto handleException(HttpRequestMethodNotSupportedException exception){
        LogEmail email = new LogEmail()
                .setToEmail("951930136@qq.com")
                .setSubject("异常报告")
                .setContext(exception.getMessage());
        emailService.senderEmail(email);
        return new ResponseDto(405,exception.getMessage());
    }
}