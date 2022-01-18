package test.cn.jinty.util;

import cn.jinty.util.ChineseUtil;
import org.junit.Test;

/**
 * 汉字 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2022/1/17
 **/
public class ChineseUtilTest {

    @Test
    public void testRandom() {
        System.out.println(ChineseUtil.random(2));
        for (int i = 0; i < 4; i++) {
            System.out.println(ChineseUtil.random(5));
        }
    }

    @Test
    public void testRandomName() {
        for (int i = 0; i < 20; i++) {
            System.out.println(ChineseUtil.randomName());
        }
    }

    @Test
    public void testRandomName1() {
        for (int i = 0; i < 20; i++) {
            System.out.println(ChineseUtil.randomName("第五"));
        }
    }

    @Test
    public void testRandomFullName() {
        for (int i = 0; i < 20; i++) {
            System.out.println(ChineseUtil.randomFullName());
        }
    }

    @Test
    public void testGetPinYin() {
        System.out.println(ChineseUtil.pinYin("我是谁"));
        // 多音字无法正确识别
        System.out.println(ChineseUtil.pinYin("差不多"));
        System.out.println(ChineseUtil.pinYin("出差"));
    }

    @Test
    public void testGetShortPinYin() {
        System.out.println(ChineseUtil.shortPinYin("我试试"));
        System.out.println(ChineseUtil.shortPinYin("永远的神"));
    }

}
