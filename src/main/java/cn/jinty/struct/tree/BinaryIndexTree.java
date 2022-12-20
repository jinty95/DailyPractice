package cn.jinty.struct.tree;

/**
 * 二进制索引树 (树状数组、Fenwick树)
 * O(logN)的时间复杂度内修改单个元素及查询区间和
 *
 * @author Jinty
 * @date 2021/6/1
 **/
public class BinaryIndexTree {

    //原数组
    private int[] nums;
    //区间和(树状数组，以二进制下标映射树型节点)
    private int[] tree;

    //构造器
    public BinaryIndexTree(int[] nums) {
        this.nums = nums;
        this.tree = new int[nums.length + 1];
        buildTree();
    }

    //构造树状数组
    private void buildTree() {
        for (int i = 0; i < nums.length; i++) {
            //x为子节点，y为父节点，则x+lowBit(x)=y
            int x = i + 1;
            int y = x + lowBit(x);
            tree[x] += nums[i];
            if (y < tree.length) {
                tree[y] += tree[x];
            }
        }
    }

    //获取最低位1
    private int lowBit(int num) {
        return num & (-num);
    }

    //更新数组的指定元素，时间复杂度O(logN)
    public void update(int index, int val) {
        //增量
        int delta = val - nums[index];
        nums[index] = val;
        //受影响的区间同步增加
        int i = index + 1;
        while (i < tree.length) {
            tree[i] += delta;
            i += lowBit(i);
        }
    }

    //前缀和查询，数组区间[0,right]，时间复杂度O(logN)
    private int sumPre(int right) {
        int sum = 0;
        int i = right + 1;
        while (i > 0) {
            sum += tree[i];
            i -= lowBit(i);
        }
        return sum;
    }

    //区间和查询，时间复杂度O(logN)
    public int sumRange(int left, int right) {
        if (left == 0) return sumPre(right);
        return sumPre(right) - sumPre(left - 1);
    }

}
