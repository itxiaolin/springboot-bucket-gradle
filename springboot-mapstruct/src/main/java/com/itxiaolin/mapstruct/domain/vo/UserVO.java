package com.itxiaolin.mapstruct.domain.vo;

import com.itxiaolin.mapstruct.domain.dto.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserVO {
    /**
     *用户ID
     */
    private Long id;
    /**
     *用户账号
     */
    private String username;
    /**
     *用户昵称
     */
    private String nickName;
    /**
     *用户类型（00系统用户）
     */
    private String userType;
    /**
     *用户邮箱
     */
    private String email;
    /**
     *手机号码
     */
    private String mobile;
    /**
     *第三方授权id
     */
    private String openId;
    /**
     *用户性别（0男 1女 2未知）
     */
    private String sex;
    /**
     *头像地址
     */
    private String avatar;
    /**
     *密码
     */
    private String password;
    /**
     *帐号状态（0正常 1停用）
     */
    private String status;
    /**
     *最后登录IP
     */
    private String loginIp;
    /**
     *最后登录时间
     */
    private String loginDate;
    /**
     *创建者
     */
    private String createBy;
    /**
     *更新者
     */
    private String updateBy;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String deleted;

    /**
     * 角色集合
     */
    List<RoleDTO> sysRoles;

    /**
     * 权限集合
     */
    private Set<String> permissions;
}
