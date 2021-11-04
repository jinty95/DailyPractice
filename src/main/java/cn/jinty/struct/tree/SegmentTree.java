package cn.jinty.struct.tree;

/**
 * 线段树
 * O(logN)的时间复杂度内修改单个元素及查询区间信息
 *
 * @author Jinty
 * @date 2021/5/31
 **/
public class SegmentTree {

    //原数组
    private int[] nums;
    //区间和数组(逻辑结构为二叉树)
    //节点为[a,b]区间和，左子为[a,(a+b)/2]区间和，右子为[(a+b)/2+1,b]区间和
    private int[] tree;

    //构造器
    public SegmentTree(int[] nums) {
        this.nums = nums;
        this.tree = new int[nums.length * 2];
        buildTree();
    }

    //从下向上构建树
    private void buildTree() {
        //叶子节点
        for (int i = nums.length, j = 0; i < tree.length; i++, j++) {
            tree[i] = nums[j];
        }
        //从倒数第二层到根节点
        for (int i = nums.length - 1; i >= 1; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    //更新数组的指定元素，时间复杂度O(logN)
    public void update(int index, int val) {
        nums[index] = val;
        int k = nums.length + index;
        tree[k] = val;
        while (k > 1) {
            k /= 2;
            tree[k] = tree[2 * k] + tree[2 * k + 1];
        }
    }

    //区间和查询，时间复杂度O(logN)
    public int sumRange(int left, int right) {
        left += nums.length;
        right += nums.length;
        int sum = 0;
        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left++];
            }
            if (right % 2 == 0) {
                sum += tree[right--];
            }
            left /= 2;
            right /= 2;
        }
        return sum;
    }

}
