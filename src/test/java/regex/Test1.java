package regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式测试
 *
 * @author jinty
 * @date 2021/4/19
 **/
public class Test1 {

    @Test
    public void test1(){
        //匹配模式
        Pattern pattern = Pattern.compile("[0-9]+");
        //输入文本
        String str1 = "1323435JBSKDFJNLAA--;,./'`1827267&^^$$#*124876FSKJB123";
        //匹配结果
        Matcher matcher = pattern.matcher(str1);
        while(matcher.find()){
            System.out.println(matcher.group());
        }
    }

}
