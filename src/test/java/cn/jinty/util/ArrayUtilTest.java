package cn.jinty.util;

import org.junit.Test;

import java.util.Arrays;

/**
 * 数组工具类 - 测试
 *
 * @author jinty
 * @date 2021/4/22
 **/
public class ArrayUtilTest {

    @Test
    public void testSwap(){
        int[] arr = ArrayUtil.generateArray(5);
        System.out.println(Arrays.toString(arr));
        ArrayUtil.swap(arr,0,4);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testReverse(){
        int[] arr = ArrayUtil.generateArray(5);
        System.out.println(Arrays.toString(arr));
        ArrayUtil.reverse(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testBinarySearch(){
        int[] arr = {0,1,2,3,4,5,6,7,8,9};
        int target = 5;
        System.out.println("Arrays is "+Arrays.toString(arr));
        System.out.println("Target is "+target);
        System.out.println("Found target in arrays, index is "+ArrayUtil.binarySearch(arr,target));
    }

    @Test
    public void testGenerateArray(){
        int[] arr = ArrayUtil.generateArray(10);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testInputArray(){
        int[] arr = ArrayUtil.inputArray();
        System.out.println(Arrays.toString(arr));
    }

}
