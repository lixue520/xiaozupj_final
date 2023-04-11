package com.hhz.service;

import com.hhz.domain.ResponseResult;
import com.hhz.domain.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();

}
