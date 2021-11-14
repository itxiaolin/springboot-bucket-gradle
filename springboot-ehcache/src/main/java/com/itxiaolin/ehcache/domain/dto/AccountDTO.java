package com.itxiaolin.ehcache.domain.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class AccountDTO implements Serializable {

    private Long id;


    private String username;


    private String mobile;


    private String password;


    private String salt;
}
