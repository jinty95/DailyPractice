package cn.jinty.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 数组工具类
 *
 * @author wangjintai
 * @date 2020/2/25.
 */
public final class ArrayUtil {

    /**
     * 打印二维数组
     *
     * @param arr 二维数组
     */
    public static void print2DArray(int[][] arr){
        for(int[] one:arr){
            System.out.println(Arrays.toString(one));
        }
    }

    /**
     * 列表转为数组
     *
     * @param list 列表
     * @return 数组
     */
    public static int[] list2Array(List<Integer> list){
        int[] ans = new int[list.size()];
        for(int i=0 ;i<list.size(); i++){
            ans[i] = list.get(i);
        }
        return ans;
    }

    /**
     * 将数组中某个元素移动到头部
     *
     * @param arr 数组
     * @param idx 索引
     */
    public static void moveToFirst(int[] arr,int idx){
        int tmp = arr[idx];
        while(idx>0){
            arr[idx] = arr[idx-1];
            idx--;
        }
        arr[0] = tmp;
    }

    /**
     * 有序数组中进行二分查找
     *
     * @param nums 有序数组
     * @param left 起点
     * @param right 终点
     * @param target 目标
     * @return 目标所在位置
     */
    public static int binarySearch(int[] nums,int left,int right,int target){
        while(left<=right){
            int mid = left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return -1;
    }

    /**
     * 随机生成指定长度的数组
     *
     * @param len 长度
     * @return 数组
     */
    public static int[] generateArray(int len){
        int[] arr = new int[len];
        Random random = new Random();
        for(int i=0; i<arr.length; i++){
            arr[i] = random.nextInt(10);
        }
        return arr;
    }

    /**
     * 键盘输入数组
     *
     * @return 数组
     */
    public static int[] inputArray(){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入数字数组，用英文的','号作为间隔:");
        String inputStr = input.nextLine();
        String[] inputArr = inputStr.split(",");
        int[] intArr = new int[inputArr.length];
        for(int i=0;i<inputArr.length;i++) {
            intArr[i] = Integer.parseInt(inputArr[i]);
        }
        input.close();
        return intArr;
    }

}
