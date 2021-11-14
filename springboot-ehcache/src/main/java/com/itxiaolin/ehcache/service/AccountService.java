package com.itxiaolin.ehcache.service;

import com.itxiaolin.ehcache.domain.dto.AccountDTO;


/**
 * 功能描述：
 * @author lxq
 * @version 1.00
 * @Date 2020/12/9
 */
public interface AccountService {
    public AccountDTO findById(Long id);
    public void remove(Long id);
    public void save(AccountDTO accountDTO);
    public void updateById(AccountDTO accountDTO);
}
