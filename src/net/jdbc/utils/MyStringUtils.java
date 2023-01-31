package net.jdbc.utils;

public class MyStringUtils {
    /**
     * 判断是否为空
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static String transLimit(String limit){
        if (limit.equals("0")){
            limit = "用户";
        }else if (limit.equals("1")){
            limit = "管理员";
        }else if (limit.equals("2")){
            limit = "超级管理员";
        }else if (limit.equals("用户")){
            limit = "0";
        }else if (limit.equals("管理员")){
            limit = "1";
        }else if (limit.equals("超级管理员")){
            limit = "2";
        }
        return limit;
    }
}
