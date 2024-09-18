package com.orioninc.ProjectRestaurants.utils;

import lombok.experimental.UtilityClass;

import java.util.Calendar;
import java.util.Date;

@UtilityClass
public class ExpireDateUtil {
    Calendar calendar = Calendar.getInstance();

    public static Date getExpireDate(int diff, Date date) {
        calendar.setTime(date);
        calendar.add(Calendar.DATE, diff);

        return calendar.getTime();
    }




}
