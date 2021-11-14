package com.itxiaolin.mapstruct.more2one;

import com.itxiaolin.mapstruct.Utils.UtilAll;
import com.itxiaolin.mapstruct.domain.dto.RoleDTO;
import com.itxiaolin.mapstruct.domain.dto.UserDTO;
import com.itxiaolin.mapstruct.domain.entity.User;
import com.itxiaolin.mapstruct.domain.model.LoginUser;
import com.itxiaolin.mapstruct.domain.model.TokenInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

/**
 * @version 1.0
 * @className: UserMoreConvert
 * @description: 多转一
 * @author: lxq
 * @date: 2021/11/13
 **/
@Mapper(
        uses = UtilAll.class
)
public interface UserMoreConvert {
    UserMoreConvert INSTANCE = Mappers.getMapper(UserMoreConvert.class);

    @Mappings({
            @Mapping(target = "sysRoles", ignore = true),
            @Mapping(target = "permissions", source = "permissions"),
    })
    UserDTO entity2dto(User sysUser, Set<String> permissions);

    @Mappings({
            @Mapping(target = "sysRoles", source = "sysRoles"),
            @Mapping(target = "permissions", ignore = true ),
    })
    UserDTO entity2dto(User sysUser, List<RoleDTO> sysRoles);

    @Mappings({
            @Mapping(target = "sysRoles", source = "sysRoles"),
            @Mapping(target = "permissions", source = "permissions"),
    })
    UserDTO entity2dto(User sysUser, List<RoleDTO> sysRoles,Set<String> permissions);

    @Mappings({
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "permissions", source = "userDTO.permissions"),
            @Mapping(target = "userDTO", source = "userDTO"),
            @Mapping(target = "userid", source = "userDTO.id"),
            @Mapping(target = "ipaddr", source = "userDTO.loginIp"),
            @Mapping(target = "loginTime",source = "userDTO.loginDate",qualifiedByName = {"UtilAll", "getTime"}),
            @Mapping(target = "token",source = "tokenInfo.token"),
            @Mapping(target = "expireTime",source = "tokenInfo.expireTime"),
    })
    LoginUser userDtoAndToken2loginUser(UserDTO userDTO, TokenInfo tokenInfo);
}
