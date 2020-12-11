package com.demo.orm.mybatis.service;

import com.demo.orm.mybatis.entity.Account;
import com.demo.orm.mybatis.mapper.AccountDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AccountService
 * @Description
 * @Author H
 * @Date 2020/12/10 14:32
 * @Version 1.0
 */
@Service
public class AccountService {
    @Resource
    private AccountDAO accountDAO;

    /**
     * 扣款操作
     *
     * @param id
     * @param money
     * @return
     */
    @Transactional
    public Account deduction(Integer id, BigDecimal money) {
        Account account = accountDAO.findById(id).orElse(null);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (account != null) {
            account.setMoney(account.getMoney().subtract(money));
            return accountDAO.saveAndFlush(account);
        }
        return null;
    }

    @Transactional
    public Account recharge(Integer id, BigDecimal money) {
        Account account = accountDAO.findById(id).orElse(null);
        if (account != null) {
            account.setMoney(account.getMoney().add(money));
            return accountDAO.saveAndFlush(account);
        }
        return null;
    }


}
