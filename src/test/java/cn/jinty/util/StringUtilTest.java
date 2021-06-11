package cn.jinty.util;

import org.junit.Test;

/**
 * 字符串 - 工具类 - 测试
 *
 * @author jinty
 * @date 2021/6/3
 **/
public class StringUtilTest {

    @Test
    public void testRandom(){
        System.out.println(StringUtil.randomNumber(10));
        System.out.println(StringUtil.randomLetter(10));
        System.out.println(StringUtil.random(10));
    }

    @Test
    public void testSnakeToCamel(){
        System.out.println(StringUtil.snakeToCamel("hello_world_are_you_ok"));
        System.out.println(StringUtil.snakeToCamel("my_name"));
        System.out.println(StringUtil.snakeToCamel("_my_name_"));
    }

    @Test
    public void testCamelToSnake(){
        System.out.println(StringUtil.camelToSnake("helloWorldAreYouOk"));
        System.out.println(StringUtil.camelToSnake("myName"));
        System.out.println(StringUtil.camelToSnake("MyName"));
    }

}
