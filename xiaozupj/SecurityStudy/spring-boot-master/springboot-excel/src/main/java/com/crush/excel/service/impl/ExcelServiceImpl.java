package com.crush.excel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.crush.excel.listener.DemoListener;
import com.crush.excel.pojo.DemoModel;
import com.crush.excel.service.ExcelService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @Author: crush
 * @Date: 2021-10-31 14:53
 * version 1.0
 */
@Service
public class ExcelServiceImpl implements ExcelService {


    @Override
    public String upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        // uploads文件夹位置
        String rootPath = request.getSession().getServletContext().getRealPath("WEB-INF/upload/");
        // 新文件名
        String newFileName = UUID.randomUUID().toString().replace("-", "") + ".xlsx";
        // 新文件
        File newFile = new File(rootPath + newFileName);
        // 判断目标文件所在目录是否存在
        if (!newFile.getParentFile().exists()) {
            // 如果目标文件所在的目录不存在，则创建父目录
            newFile.getParentFile().mkdirs();
        }
        //判断上传文件是否为空
        if (!file.isEmpty()) {
            // 将内存中的数据写入磁盘
            try {
                file.transferTo(newFile);
                return newFileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "上传失败！！";
    }

    @Override
    public String importExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        // uploads文件夹位置
        String rootPath = request.getSession().getServletContext().getRealPath("WEB-INF/upload/");
        // 新文件名
        String newFileName = UUID.randomUUID().toString().replace("-", "") + ".xlsx";
        // 新文件
        File newFile = new File(rootPath + newFileName);
        // 判断目标文件所在目录是否存在
        if (!newFile.getParentFile().exists()) {
            // 如果目标文件所在的目录不存在，则创建父目录
            newFile.getParentFile().mkdirs();
        }
        //判断上传文件是否为空
        if (!file.isEmpty()) {
            // 将内存中的数据写入磁盘
            try {
                EasyExcel.read(file.getInputStream(),DemoModel.class,new DemoListener()).sheet().doRead();
                file.transferTo(newFile);
                System.out.println(newFile.getPath());
                return newFileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "上传失败！！";
    }

    @Override
    public void down(String name, HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getSession().getServletContext().getRealPath("WEB-INF/upload/") + name;
        //获取输入流
        InputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
            //假如以中文名下载的话
            String filename = "下载文件"+name.substring(name.lastIndexOf("."));
            //转码，免得文件名中文乱码
            filename = URLEncoder.encode(filename, "UTF-8");
            //设置文件下载头
            response.addHeader("Content-Disposition", "attachment;filename=" + filename);
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            int len = 0;
            while ((len = bis.read()) != -1) {
                out.write(len);
                out.flush();
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}