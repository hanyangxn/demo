package com.example.demo2.util;


import com.example.demo2.exception.ManagementCockpitException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ResultUtil {

	public static <T> ResultDto<T> success(T obj, String msgCd, String msg){
        return new ResultDto<T>(msgCd, msg, obj);
    }

    public static <T> ResultDto<T> warning(T obj, ManagementCockpitException mce){
	    return new ResultDto<T>(mce.getCode(), mce.getMessage(), obj);
	}

	public static <T> ResultDto error(T obj, Exception e) {
        return new ResultDto<T>(CONST.RUNTIME_EXCEPTION, getStackTraceInfo(e), obj);
    }

    /**
     * add by xlqi
     * 用于异常返回
     * */
    public static <T> ResultDto error(String code, String exceptionMsg) {
        return new ResultDto<T>(code, exceptionMsg, null);
    }

    private static String getStackTraceInfo(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            return sw.toString();
        } catch(Exception ex) {
            ex.printStackTrace();
            return "";
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
    }
}
