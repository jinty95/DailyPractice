package test.cn.jinty.java.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.BitSet;

/**
 * 位图 - 测试
 * <p>
 * 位图本质上就是一个bit数组，里面每个元素都是一个bit，值为0或1
 * 一般用下标来表示一个东西，用元素值表示其是否存在
 * 好处：空间占用小，查询时间复杂度O(1)
 *
 * @author Jinty
 * @date 2023/2/23
 **/
public class BitSetTest {

    @Test
    public void testSet() {
        BitSet bitSet = new BitSet();
        System.out.println(bitSet);
        bitSet.set(5);
        System.out.println(bitSet);
        bitSet.set(10);
        System.out.println(bitSet);
        bitSet.set(15, 20);
        System.out.println(bitSet);
    }

    @Test
    public void testGet() {
        BitSet bitSet = new BitSet();
        bitSet.set(0);
        System.out.println(bitSet);
        System.out.println(bitSet.get(0));
        System.out.println(bitSet.get(1));
    }

    // 转字节数组，每8位反转后得到一个字节
    @Test
    public void testToByteArray() {
        BitSet bitSet = new BitSet();
        // 00000001
        bitSet.set(7);
        // 10000000
        System.out.println(Arrays.toString(bitSet.toByteArray()));
    }

    // 位运算
    @Test
    public void testAndOr() {
        BitSet b1 = new BitSet();
        b1.set(6);
        System.out.println("b1=" + b1);
        BitSet b2 = new BitSet();
        b2.set(7);
        System.out.println("b2=" + b2);
        // 与
        BitSet and = (BitSet) b1.clone();
        and.and(b2);
        System.out.println("b1&b2=" + and);
        // 或
        BitSet or = (BitSet) b1.clone();
        or.or(b2);
        System.out.println("b1|b2=" + or);
        // 异或
        BitSet xor = (BitSet) b1.clone();
        xor.xor(b2);
        System.out.println("b1^b2=" + xor);
    }

}
