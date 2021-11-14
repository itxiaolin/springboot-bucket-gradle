package com.itxiaolin.mapstruct.Utils;

import org.mapstruct.Named;

import java.util.Date;

@Named("UtilAll")
public class UtilAll {

    @Named("getTime")
    public static Long getTime(Date date){
        return date.getTime();
    }

}
