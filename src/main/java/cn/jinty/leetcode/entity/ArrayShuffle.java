package cn.jinty.leetcode.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 打乱数组
 *
 * @author Jinty
 * @date 2021/11/22
 **/
public class ArrayShuffle {

    private final int[] nums;
    private final int[] original;
    private final Random random;

    public ArrayShuffle(int[] nums) {
        this.nums = nums;
        this.original = Arrays.copyOf(nums, nums.length);
        random = new Random();
    }

    /**
     * 重置数组
     *
     * @return 原数组
     */
    public int[] reset() {
        System.arraycopy(original, 0, nums, 0, nums.length);
        return nums;
    }

    /**
     * 打乱数组
     * 等概率地返回一个数组排列
     *
     * @return 数组排列
     */
    public int[] shuffle() {
        // 1/n * 1/(n-1) * ... 1 = 1/n!
        int[] shuffle = new int[nums.length];
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        for (int i = 0; i < shuffle.length; i++) {
            int idx = random.nextInt(list.size());
            shuffle[i] = list.get(idx);
            list.remove(idx);
        }
        System.arraycopy(shuffle, 0, nums, 0, nums.length);
        return nums;
    }

}
