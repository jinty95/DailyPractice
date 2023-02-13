package test.cn.jinty.util;

import cn.jinty.util.RegexUtil;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/2/10
 **/
public class RegexUtilTest {

    // 测试搜索与匹配
    @Test
    public void testFindAndMatches() {
        System.out.println(RegexUtil.find("ABC", "B"));
        System.out.println(RegexUtil.find("Hello You Guys", "^H\\w+"));
        System.out.println(RegexUtil.find("Hello You Guys", "Guys$"));
        System.out.println(RegexUtil.find("Hello You Guys", "^H\\w+ Guys$"));
        System.out.println();
        System.out.println(RegexUtil.matches("ABC", "B"));
        System.out.println(RegexUtil.matches("ABC", "ABC"));
        System.out.println(RegexUtil.matches("Hello You Guys", "^H\\w+"));
        System.out.println(RegexUtil.matches("Hello You Guys", "Guys$"));
        System.out.println(RegexUtil.matches("Hello You Guys", ".+\\s+.+\\s+.+"));
    }

    // 测试匹配所有字符
    @Test
    public void testMatchAll() {
        // .不能匹配换行符
        System.out.println(RegexUtil.matches("ABC\n", ".*"));
        // 使用单行模式(?s)忽略换行符，然后使用.匹配所有字符
        System.out.println(RegexUtil.matches("ABC\n", "(?s).*"));
        // 使用[\s\S]匹配所有字符
        System.out.println(RegexUtil.matches("ABC\n", "[\\s\\S]*"));
        // 使用[\d\D]匹配所有字符
        System.out.println(RegexUtil.matches("ABC\n", "[\\d\\D]*"));
        // 使用[\w\W]匹配所有字符
        System.out.println(RegexUtil.matches("ABC\n", "[\\w\\W]*"));
    }

    // 测试零宽断言
    @Test
    public void testZeroWidthAssertion() {
        String text = "客户要求换货，已解释没有库存，无法换货";
        // 零宽断言 (不匹配任何字符，只用于定位一个位置，这个位置符合指定的规则)
        String regex = "(?=.*(无法换货|换不了|不能换货).*)(?=.*没有.*)(?=.*库存.*)|(?=.*断货.*)(?=.*换货.*)(?!.*断货王.*)(?!.*退货.*)";
        // 输出所有匹配到的子串
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int cnt = 0;
        while (matcher.find()) {
            // 当正则表达式为单纯的零宽断言时，只能匹配到零宽字符，即""
            System.out.println("符合条件的子串：" + matcher.group());
            cnt++;
        }
        System.out.println("符合条件的子串一共有：" + cnt);
    }

}
