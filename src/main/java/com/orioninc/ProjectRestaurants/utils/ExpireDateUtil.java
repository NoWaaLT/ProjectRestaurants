package com.orioninc.ProjectRestaurants.utils;

import lombok.experimental.UtilityClass;

import java.util.Calendar;
import java.util.Date;

@UtilityClass
public class ExpireDateUtil {

    public static Date expirationDate(int expireDuration) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, expireDuration);

        return calendar.getTime();
    }
}
