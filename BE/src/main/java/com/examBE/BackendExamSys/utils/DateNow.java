package com.examBE.BackendExamSys.utils;

import org.hibernate.grammars.hql.HqlParser;

import java.time.LocalDateTime;
import java.util.Date;

public class DateNow {
    public static Date getDate(){
        return new java.util.Date();
    }
    public static LocalDateTime getTime(){
//        Date currentDate = new Date();
//        return new Date(currentDate.getTime());
        return LocalDateTime.now();
    }
}
