package test.cn.jinty.util;

import cn.jinty.util.ArrayUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * 数组工具类 - 测试
 *
 * @author Jinty
 * @date 2021/4/22
 **/
public class ArrayUtilTest {

    @Test
    public void testSwap() {
        int[] arr = ArrayUtil.generateArray(5);
        System.out.println(Arrays.toString(arr));
        ArrayUtil.swap(arr, 0, 4);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testReverse() {
        int[] arr = ArrayUtil.generateArray(5);
        System.out.println(Arrays.toString(arr));
        ArrayUtil.reverse(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testBinarySearch() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 5;
        System.out.println("Arrays is " + Arrays.toString(arr));
        System.out.println("Target is " + target);
        System.out.println("Found target in arrays, index is " + ArrayUtil.binarySearch(arr, target));
    }

    @Test
    public void testShuffle() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("输入序列：" + Arrays.toString(arr));
        for (int i = 0; i < 10; i++) {
            ArrayUtil.shuffle(arr);
            System.out.println("打乱序列：" + Arrays.toString(arr));
        }
    }

    @Test
    public void testRemove() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < 10; i++) {
            System.out.println(Arrays.toString(ArrayUtil.remove(arr, i)));
        }
        System.out.println();
        System.out.println(Arrays.toString(ArrayUtil.remove(arr, 0, 9)));
        System.out.println(Arrays.toString(ArrayUtil.remove(arr, 3, 5)));
        System.out.println(Arrays.toString(ArrayUtil.remove(arr, 2, 6)));
    }

}
