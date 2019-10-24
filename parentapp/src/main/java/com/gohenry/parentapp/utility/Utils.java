package com.gohenry.parentapp.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static Date convertStringToDate(String strDate) throws ParseException {
        return formatter.parse(strDate);
    }

    public static String convertDateToString(Date date) throws ParseException {
        return formatter.format(date);
    }
}