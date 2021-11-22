package test.cn.jinty.leetcode.entity;

import cn.jinty.leetcode.entity.ArrayShuffle;
import org.junit.Test;

import java.util.Arrays;

/**
 * 打乱数组 - 测试
 *
 * @author jintai.wang
 * @date 2021/11/22
 **/
public class ArrayShuffleTest {

    @Test
    public void test() {
        int[] nums = new int[]{1,2,3,4,5};
        ArrayShuffle as = new ArrayShuffle(nums);
        System.out.println("original : " + Arrays.toString(nums));
        System.out.println("shuffle : " + Arrays.toString(as.shuffle()));
        System.out.println("reset : " + Arrays.toString(as.reset()));
        System.out.println("shuffle : " + Arrays.toString(as.shuffle()));
        System.out.println("reset : " + Arrays.toString(as.reset()));
        System.out.println("shuffle : " + Arrays.toString(as.shuffle()));
        System.out.println("reset : " + Arrays.toString(as.reset()));
        System.out.println("shuffle : " + Arrays.toString(as.shuffle()));
    }

}
