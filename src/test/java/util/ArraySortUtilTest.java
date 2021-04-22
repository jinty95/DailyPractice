package util;

import cn.jinty.utils.ArraySortUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * 数组排序工具类 - 测试
 *
 * @author jinty
 * @date 2021/4/22
 **/
public class ArraySortUtilTest {

    @Test
    public void testBubbleSort(){
        int[] arr = {3,5,7,8,9,1,3,4,6,7,8,0,3,4};
        System.out.println(Arrays.toString(arr));
        ArraySortUtil.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testInsertSort(){
        int[] arr = {3,5,7,8,9,1,3,4,6,7,8,0,3,4};
        System.out.println(Arrays.toString(arr));
        ArraySortUtil.insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testSelectSort(){
        int[] arr = {3,5,7,8,9,1,3,4,6,7,8,0,3,4};
        System.out.println(Arrays.toString(arr));
        ArraySortUtil.selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testQuickSort(){
        int[] arr = {3,5,7,8,9,1,3,4,6,7,8,0,3,4};
        System.out.println(Arrays.toString(arr));
        ArraySortUtil.quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testMergeSort(){
        int[] arr = {3,5,7,8,9,1,3,4,6,7,8,0,3,4};
        System.out.println(Arrays.toString(arr));
        ArraySortUtil.mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testHeapSort(){
        int[] arr = {3,5,7,8,9,1,3,4,6,7,8,0,3,4};
        System.out.println(Arrays.toString(arr));
        ArraySortUtil.heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
