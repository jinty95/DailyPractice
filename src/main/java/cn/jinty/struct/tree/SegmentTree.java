package cn.jinty.struct.tree;

/**
 * 线段树
 * O(logN)的时间复杂度内修改单个元素及查询区间和
 *
 * @author Jinty
 * @date 2021/5/31
 **/
public class SegmentTree {

    //原数组
    private int[] nums;
    //区间和(以数组模拟完全二叉树，tree[1]为根节点，代表整个区间和，向下对半划分区间，直到叶子节点)
    private int[] tree;

    //构造器
    public SegmentTree(int[] nums) {
        this.nums = nums;
        this.tree = new int[nums.length * 2];
        buildTree();
    }

    //从下向上构建树
    private void buildTree() {
        //叶子节点(并非一定在同一层)
        for (int i = nums.length, j = 0; i < tree.length; i++, j++) {
            tree[i] = nums[j];
        }
        //从叶子节点往上求和，一直到根节点
        for (int i = nums.length - 1; i >= 1; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    //更新数组的指定元素，时间复杂度O(logN)
    public void update(int index, int val) {
        //更新叶子节点值
        nums[index] = val;
        int k = nums.length + index;
        //更新叶子节点相关区间和
        tree[k] = val;
        while (k > 1) {
            k /= 2;
            tree[k] = tree[2 * k] + tree[2 * k + 1];
        }
    }

    //区间和查询，时间复杂度O(logN)
    public int sumRange(int left, int right) {
        //下层到叶子节点
        left += nums.length;
        right += nums.length;
        int sum = 0;
        while (left <= right) {
            //左奇，叠加当前区间和，右移再向上；左偶，直接向上
            if (left % 2 == 1) {
                sum += tree[left++];
            }
            left /= 2;
            //右偶，叠加当前区间和，左移再向上；右奇，直接向上
            if (right % 2 == 0) {
                sum += tree[right--];
            }
            right /= 2;
        }
        return sum;
    }

}
