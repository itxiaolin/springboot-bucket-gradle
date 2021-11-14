package com.itxiaolin.mapstruct.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 *  角色信息表DTO
 */
@Data
public class RoleDTO {
    /**
     * 角色序号
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限
     */
    private String roleKey;

    /**
     * 角色排序
     */
    private Integer roleSort;

    /**
     * 菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示）
     */
    private Boolean menuCheckStrictly;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;
    /**
     * 创建者
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

    /**
     * 菜单组
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long[] menuIds;
}
