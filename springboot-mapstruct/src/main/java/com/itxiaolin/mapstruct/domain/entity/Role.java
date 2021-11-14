package com.itxiaolin.mapstruct.domain.entity;


import lombok.Data;

import java.io.Serializable;

/**
 *  角色信息表
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     *角色名称
     */
    private String roleName;
    /**
     *角色权限字符串
     */
    private String roleKey;
    /**
     *显示顺序
     */
    private Integer roleSort;
    /**
     *数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    private String dataScope;
    /**
     *菜单树选择项是否关联显示
     */
    private Boolean menuCheckStrictly;
    /**
     *角色状态（0正常 1停用）
     */
    private String status;
    /**
     *创建者
     */
    private String createBy;
    /**
     *更新者
     */
    private String updateBy;
    /**
     *备注
     */
    private String remark;
    /**
     *删除标志（0代表存在 1代表删除）
     */
    private String deleted;
    /**
     *租户id
     */
    private String tenantId;
}
