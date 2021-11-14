package com.itxiaolin.ehcache.controller;

import com.itxiaolin.ehcache.domain.dto.AccountDTO;
import com.itxiaolin.ehcache.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述：
 *
 * @author lxq
 * @version 1.00
 * @Date 2020/12/9
 */
@Slf4j
@RestController
@RequestMapping("account")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }

    @PostMapping()
    public void save(@RequestBody AccountDTO accountDTO) {
        log.info("accountDTO:{}",accountDTO);
        accountService.save(accountDTO);
    }
    @PutMapping()
    public void put(@RequestBody AccountDTO accountDTO) {
        log.info("accountDTO:{}",accountDTO);
        accountService.updateById(accountDTO);
    }

    @DeleteMapping("/{id}")
    public String evict(@PathVariable Long id) {
        accountService.remove(id);
        return "ok";
    }

    @GetMapping("/{id}")
    public AccountDTO cacheable(@PathVariable Long id) {
        AccountDTO dto = accountService.findById(id);
        System.out.println(dto);
        return dto;
    }
}
