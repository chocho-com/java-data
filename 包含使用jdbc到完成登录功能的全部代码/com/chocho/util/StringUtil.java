package com.chocho.util;
/*String类的工具*/
public class StringUtil {
    public static boolean isEmpty(String s){
        if(s == null || "".equals(s)){
            return true;
        }
        return false;
    }
}
