package test.cn.jinty.util.math;

import cn.jinty.util.math.NumberAddUtil;
import junit.framework.Assert;
import org.junit.Test;

/**
 * 数字加法 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/6/30
 **/
public class NumberAddUtilTest {

    @Test
    public void testByteAdd() {
        Byte[][] numArr = {
                {null, null, null}, {null, 1, 1}, {1, null, 1}, {1, 1, 2}
        };
        for (Byte[] num : numArr) {
            Assert.assertEquals(NumberAddUtil.add(num[0], num[1]), num[2]);
        }
    }

    @Test
    public void testShortAdd() {
        Short[][] numArr = {
                {null, null, null}, {null, 1, 1}, {1, null, 1}, {1, 1, 2}
        };
        for (Short[] num : numArr) {
            Assert.assertEquals(NumberAddUtil.add(num[0], num[1]), num[2]);
        }
    }

    @Test
    public void testIntegerAdd() {
        Integer[][] numArr = {
                {null, null, null}, {null, 1, 1}, {1, null, 1}, {1, 1, 2}
        };
        for (Integer[] num : numArr) {
            Assert.assertEquals(NumberAddUtil.add(num[0], num[1]), num[2]);
        }
    }

    @Test
    public void testLongAdd() {
        Long[][] numArr = {
                {null, null, null}, {null, 1L, 1L}, {1L, null, 1L}, {1L, 1L, 2L}
        };
        for (Long[] num : numArr) {
            Assert.assertEquals(NumberAddUtil.add(num[0], num[1]), num[2]);
        }
    }

    @Test
    public void testFloatAdd() {
        Float[][] numArr = {
                {null, null, null}, {null, 1.0f, 1.0f}, {1.0f, null, 1.0f}, {1.0f, 1.0f, 2.0f}
        };
        for (Float[] num : numArr) {
            Assert.assertEquals(NumberAddUtil.add(num[0], num[1]), num[2]);
        }
    }

    @Test
    public void testDoubleAdd() {
        Double[][] numArr = {
                {null, null, null}, {null, 1.0, 1.0}, {1.0, null, 1.0}, {1.0, 1.0, 2.0}
        };
        for (Double[] num : numArr) {
            Assert.assertEquals(NumberAddUtil.add(num[0], num[1]), num[2]);
        }
    }

}
