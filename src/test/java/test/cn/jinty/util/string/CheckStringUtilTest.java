package test.cn.jinty.util.string;

import cn.jinty.util.string.CheckStringUtil;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * 检查字符串 - 工具类 - 测试
 *
 * @author jintai.wang
 * @date 2023/6/20
 **/
public class CheckStringUtilTest {

    @Test
    public void testIs() {
        String[] arr = {
                "Hello", "12345", "HELLO", "hello"
        };
        for (String s : arr) {
            System.out.println(s + " 是字母：" + CheckStringUtil.isLetter(s));
            System.out.println(s + " 是数字：" + CheckStringUtil.isDigit(s));
            System.out.println(s + " 是大写：" + CheckStringUtil.isUpperCase(s));
            System.out.println(s + " 是小写：" + CheckStringUtil.isLowerCase(s));
            System.out.println();
        }
    }

    @Test
    public void testIsNumeric() {
        String[] arr = {
                "0", "-1", "+0.01", "88", "99.9", "9E", "2E64", "ABC", "+-100", "0.0.0", "0."
        };
        for (String s : arr) {
            boolean flag = CheckStringUtil.isNumeric(s);
            System.out.print(s + " 是数值：" + flag);
            if (flag) {
                System.out.print("，解析结果：" + new BigDecimal(s).stripTrailingZeros().toPlainString());
            }
            System.out.println();
        }
    }

    @Test
    public void testContains() {
        String[] arr = {
                "Hello", "123@qq.com"
        };
        for (String s : arr) {
            System.out.println(s + " 包含字母：" + CheckStringUtil.containsLetter(s));
            System.out.println(s + " 包含大写字母：" + CheckStringUtil.containsUpperCase(s));
            System.out.println(s + " 包含小写字母：" + CheckStringUtil.containsLowerCase(s));
            System.out.println(s + " 包含数字：" + CheckStringUtil.containsDigit(s));
            System.out.println(s + " 包含特殊字符：" + CheckStringUtil.containsSpecialChar(s));
            System.out.println();
        }
    }

    @Test
    public void testCheckPassword() {
        String[] arr = {
                "123456", "Abc123", "Abc123?", "Abcd123?", "Abcd123?123456789"
        };
        for (String s : arr) {
            System.out.println(s + " 是合法密码：" + CheckStringUtil.checkPassword(s));
        }
    }

    @Test
    public void testIsLikeCnPhoneNum() {
        String[] arr = {
                "99988889999", "12088889999", "15688889999", "13688889999", "19988889999", "199AAAABBBB"
        };
        for (String s : arr) {
            System.out.println(s + " 是手机号码：" + CheckStringUtil.checkPhoneNum(s));
        }
    }

}
