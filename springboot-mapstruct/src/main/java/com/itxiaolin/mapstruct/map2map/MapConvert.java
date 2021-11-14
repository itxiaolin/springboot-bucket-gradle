package com.itxiaolin.mapstruct.map2map;

import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.Map;

@Mapper
public interface MapConvert {
    MapConvert INSTANCE = Mappers.getMapper(MapConvert.class);
    @MapMapping(valueDateFormat = "yyyyMMdd")
    Map<String, String> longDateMapToStringStringMap(Map<Long, Date> source);
}
