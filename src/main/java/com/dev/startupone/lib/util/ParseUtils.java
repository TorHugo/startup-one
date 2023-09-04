package com.dev.startupone.lib.util;

public class ParseUtils {

    private ParseUtils(){}
    public static String parseString(final Object o){
        return String.valueOf(o);
    }

    public static Long parseLong(final Object o){
        return Long.valueOf(o.toString());
    }
}
