package com.itxiaolin.mapstruct.Utils;

import org.mapstruct.Named;

import java.util.Date;

/**
 * @version 1.0
 * @className: UtilAll
 * @description: TODO
 * @author: lxq
 * @date: 2021/11/14
 **/
@Named("UtilAll")
public class UtilAll {

    @Named("getTime")
    public static Long getTime(Date date){
        return date.getTime();
    }

}
