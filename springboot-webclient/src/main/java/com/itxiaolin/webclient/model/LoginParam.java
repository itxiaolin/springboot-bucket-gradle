package com.itxiaolin.webclient.model;

import lombok.Data;

/**
 * @author lxq
 * @version 1.0
 * @description: 登录认证接口参数
 * @date 2021/1/14 10:02
 */
@Data
public class LoginParam {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;


}
