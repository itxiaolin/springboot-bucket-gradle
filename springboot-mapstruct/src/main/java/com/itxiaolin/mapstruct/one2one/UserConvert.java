package com.itxiaolin.mapstruct.one2one;
import com.itxiaolin.mapstruct.domain.dto.UserDTO;
import com.itxiaolin.mapstruct.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 功能描述：User转化接口
 * @author lxq
 * @version 1.00
 * @date 2021/1/21 10:37
 */
@Mapper(
        uses = RoleConvert.class
)
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    /**
     * UserDTO比User多了角色集合和权限集合属性
     */
    User dto2entity(UserDTO userDTO);

    /**
     * 忽略属性User中没有的属性
     */
    @Mappings({
            @Mapping(target = "sysRoles", ignore = true),
            @Mapping(target = "permissions", ignore = true),
    })
    UserDTO entity2dto(User sysUser);

    /**
     * 默认浅拷贝
     */
    UserDTO dto2dto(UserDTO userDTO);

    /**
     * 深拷贝
     */
    @Mapping(target = "sysRoles",source = "sysRoles",qualifiedByName = {"RoleConvert", "dto2dto"})
    UserDTO deepDto2dto(UserDTO userDTO);

}