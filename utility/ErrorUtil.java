package com.mindskip.xzs.utility;


/**
 * @version 3.5.0
 * @description: The type Error util.
 * Copyright (C),    2024, study
 * @date 2021/12/25 9:45
 */
public class ErrorUtil {
    /**
     * Parameter error format string.
     *
     * @param field the field
     * @param msg   the msg
     * @return the string
     */
    public static String parameterErrorFormat(String field, String msg) {
        return "【" + field + " : " + msg + "】";
    }
}
