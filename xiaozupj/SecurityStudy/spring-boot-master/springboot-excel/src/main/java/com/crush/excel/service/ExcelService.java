package com.crush.excel.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: crush
 * @Date: 2021-10-31 14:53
 * version 1.0
 */
public interface ExcelService {


    String upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response);
    String importExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response);
    void down(String name,HttpServletRequest request,HttpServletResponse response);
}
