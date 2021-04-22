package cn.jinty.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 数组工具类
 *
 * @author wangjintai
 * @date 2020/2/25.
 */
public final class ArrayUtil {

    public static void print2DArray(int[][] arr){
        for(int[] one:arr){
            System.out.println(Arrays.toString(one));
        }
    }

    public static int[] list2Array(List<Integer> list){
        int[] ans = new int[list.size()];
        for(int i=0 ;i<list.size(); i++){
            ans[i] = list.get(i);
        }
        return ans;
    }

    public static int[] generateIntArray(int len){
        int[] arr = new int[len];
        Random random = new Random();
        for(int i=0; i<arr.length; i++){
            arr[i] = random.nextInt(10);
        }
        return arr;
    }

    public static void moveToFirst(int[] arr,int idx){
        int tmp = arr[idx];
        while(idx>0){
            arr[idx] = arr[idx-1];
            idx--;
        }
        arr[0] = tmp;
    }

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

}
