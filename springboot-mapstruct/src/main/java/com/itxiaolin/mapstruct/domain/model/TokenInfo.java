package com.itxiaolin.mapstruct.domain.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenInfo implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String token;
    private Long expireTime;
}
