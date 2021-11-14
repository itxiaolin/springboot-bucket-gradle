package com.itxiaolin.ehcache.service.impl;

import com.itxiaolin.ehcache.domain.convert.AccountConvert;
import com.itxiaolin.ehcache.domain.dto.AccountDTO;
import com.itxiaolin.ehcache.domain.entity.Account;
import com.itxiaolin.ehcache.mapper.AccountMapper;
import com.itxiaolin.ehcache.service.AccountService;
import com.itxiaolin.ehcache.service.CacheService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lxq
 * @version 1.00
 * @Date 2020/12/9
 */

@Service
@CacheConfig(cacheNames = {"demo-cache"})
public class AccountServiceImpl implements AccountService {
    private AccountMapper accountMapper;
    private CacheService cacheService;
    public AccountServiceImpl( AccountMapper accountMapper,CacheService cacheService){
        this.accountMapper=accountMapper;
        this.cacheService= cacheService;
    }

    /**
     *@Cacheable缓存key为account__+id数据到缓存Account中
     * @param id
     * @return
     */

    @Cacheable( key = "'account_'+ #id")
    @Override
    public AccountDTO findById(Long id){
        return AccountConvert.INSTANCE.entity2dto(accountMapper.selectById(id));
    }
    /**
     * @param id
     * @CacheEvict从缓存demo-cache中删除key为account_id的数据
     */
    @Transactional
    @CacheEvict(key = "'account_'+ #id")
    @Override
    public void remove(Long id) {
        accountMapper.deleteById(id);
    }

    /**
     * 保存数据，以及保存到缓存中
     * @param accountDTO
     */
    @Transactional
    @Override
    public void save(AccountDTO accountDTO) {
        Account account = AccountConvert.INSTANCE.dto2entity(accountDTO);
        accountMapper.insert(account);
        cacheService.put("account_"+accountDTO.getId(),AccountConvert.INSTANCE.entity2dto(account));//放入缓存中
    }

    /**
     * 更新数据以及更新缓存的数据
     * @param accountDTO
     */
    @Transactional
    @Override
    public void updateById(AccountDTO accountDTO) {
        Account account = AccountConvert.INSTANCE.dto2entity(accountDTO);
        accountMapper.updateById(account);
        cacheService.put("account_"+accountDTO.getId(),AccountConvert.INSTANCE.entity2dto(account));//修改缓存中
    }
}
