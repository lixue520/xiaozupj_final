package com.crush.security_code.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.crush.security_code.entity.ImgTypeConstant;
import com.crush.security_code.entity.VerifyVO;
import com.crush.security_code.service.IVerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/verifyImage")
public class MainController {



    @Autowired
    private  IVerifyCodeService iVerifyCodeService;


    /**
     * 获取验证码图片,
     * 验证码存储在redis中,key为uuid
     * @throws IOException
     */
    @GetMapping("/")
    public VerifyVO getVerifyImage() throws IOException {
        try (java.io.ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            String verifyUuid = IdUtil.simpleUUID();
            Image image = iVerifyCodeService.getVerifyCodeImage(verifyUuid);

            ImageIO.write((RenderedImage)image, ImgTypeConstant.PNG.getSuffix(), byteArrayOutputStream);
            String verifyBase64 = Base64.encode(byteArrayOutputStream.toByteArray());

            verifyBase64 = "data:image/png;base64," + verifyBase64;

            VerifyVO verifyVO = new VerifyVO()
                    .setImg(verifyBase64)
                    .setUuid(verifyUuid);
            return verifyVO;
        }
    }

}
