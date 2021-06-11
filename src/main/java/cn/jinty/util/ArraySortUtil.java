package cn.jinty.util;

import java.util.Random;

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
     * 快速排序
     * 时间复杂度O(NlogN)
     *
     * @param arr 数组
     */
    public static void quickSort(int[] arr){
        if(arr==null) return;
        quickSort(arr,0,arr.length-1);
    }
    private static void quickSort(int[] arr, int begin, int end){
        if(begin<end){
            int mid = oneQucikSort(arr,begin,end);
            quickSort(arr,begin,mid-1);
            quickSort(arr,mid+1,end);
        }
    }
    private static int oneQucikSort(int[] arr, int begin, int end){
        int mid=begin;
        boolean inLeft = true;
        while(begin<end) {
            if(inLeft) {
                //mid在左，与右端比较
                if (arr[mid] >= arr[end]) {
                    swap(arr, mid, end);
                    mid = end;
                    begin++;
                    inLeft = false;
                } else {
                    end--;
                }
            }else{
                //mid在右，与左端比较
                if(arr[mid]<=arr[begin]) {
                    swap(arr,mid,begin);
                    mid=begin;
                    end--;
                    inLeft = true;
                } else {
                    begin++;
                }
            }
        }
        return mid;
    }

    /**
     * 归并排序
     * 时间复杂度O(NlogN)
     *
     * @param arr 数组
     */
    public static void mergeSort(int[] arr){
        if(arr==null) return;
        mergeSort(arr,0,arr.length-1);
    }
    private static void mergeSort(int[] arr,int begin,int end){
        if(begin<end){
            int mid = begin+(end-begin)/2;
            mergeSort(arr,begin,mid);
            mergeSort(arr,mid+1,end);
            merge(arr,begin,mid,end);
        }
    }
    private static void merge(int[] arr,int begin,int mid,int end){
        int[] temp=new int[end-begin+1];
        int i=begin;
        int j=mid+1;
        int k=0;
        while(i<=mid&&j<=end) {
            if(arr[i]<=arr[j]) {
                temp[k++]=arr[i++];
            }else {
                temp[k++]=arr[j++];
            }
        }
        while(i<=mid) {
            temp[k++]=arr[i++];
        }
        while(j<=end) {
            temp[k++]=arr[j++];
        }
        for(int m=0;m<temp.length;m++) {
            arr[m+begin]=temp[m];
        }
    }

    /**
     * 堆排序
     * 时间复杂度O(NlogN)
     *
     * @param arr 数组
     */
    public static void heapSort(int[] arr) {
        //初始化大根堆
        for(int i=arr.length/2-1;i>=0;i--) {
            //从最后一个非叶子结点开始，从下往上调用调整函数，构建一个大根堆
            heapAdjust(arr,i,arr.length);
        }
        //调整大根堆
        for(int j=arr.length-1;j>0;j--) {
            //将堆顶与堆尾进行互换，堆大小减一
            swap(arr,0,j);
            //堆顶以下全都有序，故仅需堆顶调用调整函数
            heapAdjust(arr, 0, j);
        }
    }
    //大根堆调整函数，i是堆顶，length-1是堆尾
    private static void heapAdjust(int[] arr,int i,int length) {
        //此函数建立在堆顶以下有序的情况
        int temp=arr[i];
        //若i为根节点，则i*2+1为左子，i*2+2为右子
        for(int k=i*2+1;k<length;k=k*2+1) {
            //右子存在且右子大于左子，指向右子
            if(k+1<length&&arr[k]<arr[k+1]) {
                k++;
            }
            //子节点大于根节点
            if(arr[k]>temp) {
                arr[i]=arr[k];
                i=k;
            }else {
                break;
            }
        }
        //找到合适的位置
        arr[i]=temp;
    }

    /**
     * 计数排序
     * 时间复杂度O(N)
     *
     * @param arr 数组
     */
    public static void countSort(int[] arr){
        if(arr==null || arr.length==0) return;
        int max = arr[0], min = arr[0];
        for(int num:arr){
            max = Math.max(max,num);
            min = Math.min(min,num);
        }
        countSort(arr,min,max);
    }
    private static void countSort(int[] arr, int min, int max){
        //计数
        int[] countArr=new int[max-min+1];
        for (int i : arr) {
            countArr[i - min] += 1;
        }
        //恢复
        int index=0;
        for(int i=0;i<countArr.length;i++) {
            if(countArr[i]!=0) {
                int temp=countArr[i];
                while(temp>0) {
                    arr[index++]=i+min;
                    temp--;
                }
            }
        }
    }

    /**
     * 基数排序
     * 时间复杂度O(DN) 其中D为数组最大值的十进制数位长度
     *
     * @param arr 数组
     */
    public static void radixSort(int[] arr){
        if(arr==null || arr.length==0) return;
        //求最大值
        int max = arr[0];
        for(int i : arr) max = Math.max(max,i);
        //求最大值的十进制数位长度
        int d = max==0 ? 1:0;
        while(max>0){
            max /= 10;
            d++;
        }
        radixSort(arr,d);
    }
    private static void radixSort(int[] arr,int d){
        //n代表每个数位对应的权值
        int n=1;
        //m代表当前被排序的数位
        int m=1;
        //k作为滑动的指针
        int k=0;
        //收集某轮排序的数据
        int[][] temp=new int[10][arr.length];
        //标识temp中的有效数据量
        int[] count=new int[10];
        while(m<=d) {
            //这里采用最小位优先
            for (int j : arr) {
                int radix = (j / n) % 10;
                temp[radix][count[radix]++] = j;
            }
            //根据每个数出现的次数，去二维数组中取出覆盖原数组，完成一轮排序
            for(int i=0;i<count.length;i++) {
                int len=0;
                while(count[i]!=0) {
                    arr[k++]=temp[i][len++];
                    count[i]--;
                }
            }
            //一轮排序过后权值增大
            n=n*10;
            //一轮排序过后数位往前移1
            m++;
            //滑动指针归零，用于下轮排序使用
            k=0;
        }
    }

    /**
     * 猴子排序
     * 打乱数组，直到有序，时间复杂度随缘
     *
     * @param arr 数组
     */
    public static void monkeySort(int[] arr){
        if(arr==null) return;
        Random random = new Random();
        int count = 0;
        while(!isOrdered(arr,true)){
            int len = arr.length;
            while(len>0){
                int idx1 = random.nextInt(arr.length);
                int idx2 = random.nextInt(arr.length);
                while(idx2==idx1){
                    idx2 = random.nextInt(arr.length);
                }
                swap(arr,idx1,idx2);
                len--;
            }
            count++;
        }
        System.out.println("打乱次数="+count);
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

    /**
     * 判断数组是否有序
     *
     * @param arr 数组
     * @param ascend 是否升序，是则判断升序，否则判断降序
     * @return 是否有序
     */
    private static boolean isOrdered(int[] arr, boolean ascend){
        if(arr==null || arr.length<2) return true;
        if(ascend){
            for(int i=1;i<arr.length;i++){
                if(arr[i-1]>arr[i]) return false;
            }
        }else{
            for(int i=1;i<arr.length;i++){
                if(arr[i-1]<arr[i]) return false;
            }
        }
        return true;
    }

}
