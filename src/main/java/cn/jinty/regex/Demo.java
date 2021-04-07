package cn.jinty.regex;

import java.util.regex.Pattern;

/**
 * 正则表达式
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class Demo {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^[0-9]+");
        String str1 = "1323435JBSKDFJNLAA--;,./'`1827267&^^$$#*124876FSKJB123";
        String str2 = "1234567879";
        System.out.println(pattern.matcher(str1).find());
        System.out.println(pattern.matcher(str2).find());
    }
}
