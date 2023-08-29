package com.dev.startupone.lib.util;

import java.util.Objects;

public class ValidationUtils {

    public static Object isNullOrElse(final Object oldObject, final Object newObject){
        return Objects.isNull(oldObject) ? newObject : oldObject;
    }
    public static Object isNull(final Object object){
        return Objects.isNull(object) ? null : object;
    }
    public static boolean nonNull(final Object object){
        return Objects.nonNull(object);
    }
}
