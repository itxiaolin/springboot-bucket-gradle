package com.itxiaolin.mapstruct.list2list;

import com.itxiaolin.mapstruct.domain.dto.UserDTO;
import com.itxiaolin.mapstruct.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserListConvert {
    UserListConvert INSTANCE = Mappers.getMapper(UserListConvert.class);

    List<User> dtoList2entityList(List<UserDTO> userDTOList);

    List<UserDTO> entityList2dtoList(List<User> userList);

    @Mappings({
            @Mapping(target = "sysRoles", ignore = true),
            @Mapping(target = "permissions", ignore = true),
    })
    UserDTO entity2dto(User user);
}
