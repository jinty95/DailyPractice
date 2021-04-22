package cn.jinty.utils;

/**
 * 数组排序工具
 *
 * @author jinty
 * @date 2021/4/22
 **/
public final class ArraySortUtil {

    /**
     * 冒泡排序
     * 时间复杂度O(N^2)，最优情况下O(N)
     *
     * @param arr 数组
     */
    public static void bubbleSort(int[] arr){
        if(arr==null) return;
        //是否发生交换
        boolean swap=true;
        //i标识无序数组的尾部
        for(int i = arr.length-1; i>0 && swap; i--) {
            swap=false;
            //j从0遍历到i
            for(int j = 0; j<i; j++) {
                if(arr[j]>arr[j+1]) {
                    swap(arr,j,j+1);
                    swap=true;
                }
            }
        }
    }

    /**
     * 插入排序
     * 时间复杂度O(N^2)，最优情况下为O(N)
     *
     * @param arr 数组
     */
    public static void insertSort(int[] arr){
        if(arr==null) return;
        //左边为有序数组，遍历右边，将元素一个一个插入有序数组中
        for(int i=1;i<arr.length;i++) {
            for(int j=i;j>0;j--){
                if(arr[j-1]<=arr[j]) break;
                else{
                    swap(arr,j-1,j);
                }
            }
        }
    }

    /**
     * 选择排序
     * 时间复杂度O(N^2)
     *
     * @param arr 数组
     */
    public static void selectSort(int[] arr){
        if(arr==null) return;
        //左边为有序数组，遍历右边，选择最小的一个元素，加入有序数组的右边
        for(int i=0;i<arr.length;i++) {
            //用k记录每次比较后的较小值的索引
            int k=i;
            for(int j=i;j<arr.length;j++) {
                if(arr[k]>arr[j]) k=j;
            }
            swap(arr,i,k);
        }
    }

    /**
     * 数组元素交换
     *
     * @param arr 数组
     * @param a 索引1
     * @param b 索引2
     */
    private static void swap(int[] arr, int a, int b){
        if(a==b) return;
        arr[a] ^= arr[b];
        arr[b] ^= arr[a];
        arr[a] ^= arr[b];
    }

}
