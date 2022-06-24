package cn.jinty.util;

import java.util.Random;

/**
 * 数组 - 工具类
 *
 * @author Jinty
 * @date 2020/2/25.
 */
public final class ArrayUtil {

    /**
     * 是否为空
     *
     * @param arr 数组
     * @return 是否为空
     */
    public static boolean isEmpty(int[] arr) {
        return arr == null || arr.length == 0;
    }

    /**
     * 是否非空
     *
     * @param arr 数组
     * @return 是否非空
     */
    public static boolean isNotEmpty(int[] arr) {
        return !isEmpty(arr);
    }

    /**
     * 数组中元素交换
     *
     * @param arr 数组
     * @param a   索引a
     * @param b   索引b
     */
    public static void swap(int[] arr, int a, int b) {
        if (a == b) return;
        arr[a] ^= arr[b];
        arr[b] ^= arr[a];
        arr[a] ^= arr[b];
    }

    /**
     * 数组反转
     *
     * @param arr 数组
     */
    public static void reverse(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    /**
     * 二分查找
     *
     * @param arr    有序数组
     * @param target 目标
     * @return 目标所在位置
     */
    public static int binarySearch(int[] arr, int target) {
        return binarySearch(arr, 0, arr.length - 1, target);
    }

    /**
     * 二分查找
     *
     * @param arr    有序数组
     * @param left   起点
     * @param right  终点
     * @param target 目标
     * @return 目标所在位置
     */
    public static int binarySearch(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 生成指定长度的数组(值为0~9的随机数)
     *
     * @param len 长度
     * @return 数组
     */
    public static int[] generateArray(int len) {
        int[] arr = new int[len];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10);
        }
        return arr;
    }

    /**
     * 打乱数组
     *
     * @param arr 数组
     */
    public static void shuffle(int[] arr) {
        Random random = new Random();
        int a, b;
        for (int i = 0; i < arr.length; i++) {
            a = random.nextInt(arr.length);
            b = random.nextInt(arr.length);
            swap(arr, a, b);
        }
    }

}
