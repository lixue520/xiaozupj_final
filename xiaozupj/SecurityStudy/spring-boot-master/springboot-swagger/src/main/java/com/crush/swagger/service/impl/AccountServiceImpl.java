package com.crush.swagger.service.impl;

import com.crush.swagger.entity.Account;
import com.crush.swagger.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: crush
 * @Date: 2021-07-29 17:32
 * version 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static List<Account> accountList = new ArrayList<Account>();

    @Override
    public boolean register(Account account) {
        accountList.add(account);
        return true;
    }

    @Override
    public List<Account> select() {
        return accountList;
    }

}
