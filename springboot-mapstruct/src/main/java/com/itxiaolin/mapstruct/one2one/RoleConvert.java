package com.itxiaolin.mapstruct.one2one;

import com.itxiaolin.mapstruct.domain.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
@Named("RoleConvert")
public interface RoleConvert {
    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    /**
     * 拷贝对象，提供user深拷贝接口
     */
    @Named("dto2dto")
    RoleDTO dto2dto(RoleDTO roleDTO);
}
