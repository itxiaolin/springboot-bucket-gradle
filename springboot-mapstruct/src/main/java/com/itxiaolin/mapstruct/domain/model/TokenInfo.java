package com.itxiaolin.mapstruct.domain.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @className: TokenInfo
 * @description: TODO
 * @author: lxq
 * @date: 2021/11/14
 **/
@Data
public class TokenInfo implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String token;
    private Long expireTime;
}
