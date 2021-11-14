package com.itxiaolin.ehcache.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能描述：
 *
 * @author 林贤钦
 * @version 1.00
 * @Date 2020/12/10
 */
@Data
@TableName("account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value="ID",type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 手机号
     */
    @TableField("MOBILE")
    private String mobile;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 盐
     */
    @TableField("SALT")
    private String salt;


}

