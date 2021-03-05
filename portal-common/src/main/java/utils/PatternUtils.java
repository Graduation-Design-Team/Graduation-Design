package utils;

import java.util.regex.Pattern;

public class PatternUtils {

    /**
     * 判断字符串是否为数字
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}
