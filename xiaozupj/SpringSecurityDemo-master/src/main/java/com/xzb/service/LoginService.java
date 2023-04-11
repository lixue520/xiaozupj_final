package com.xzb.service;

import com.xzb.domain.ResponseResult;
import com.xzb.domain.User;

public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
