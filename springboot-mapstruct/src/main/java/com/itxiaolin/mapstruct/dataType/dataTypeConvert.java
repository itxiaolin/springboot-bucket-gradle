package com.itxiaolin.mapstruct.dataType;

import com.itxiaolin.mapstruct.Utils.UtilAll;
import com.itxiaolin.mapstruct.domain.dto.UserDTO;
import com.itxiaolin.mapstruct.domain.vo.UserVO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.List;

@Mapper(
        uses = UtilAll.class
)
public interface dataTypeConvert {
    dataTypeConvert INSTANCE = Mappers.getMapper(dataTypeConvert.class);

    @IterableMapping(numberFormat = "$#.00")
    List<String> listInteger2ListString(List<Integer> prices);

    @IterableMapping(dateFormat = "yyyyMMdd")
    List<String> dateList2stringList(List<Date> dates);

    @IterableMapping(qualifiedByName = {"UtilAll", "getTime"})
    List<Long> dateList2longList(List<Date> dates);

    @Mapping(target = "loginDate",source = "loginDate",dateFormat = "yyyyMMdd")
    UserVO userDto2userVO(UserDTO userDTO);
}
