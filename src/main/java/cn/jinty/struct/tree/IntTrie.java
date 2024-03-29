package cn.jinty.struct.tree;

/**
 * 前缀树 - 基于01存储int型整数
 *
 * @author Jinty
 * @date 2021/5/23
 */
public class IntTrie {

    //子节点
    private IntTrie[] child = new IntTrie[2];

    //空参构造器
    public IntTrie() {
    }

    //插入一个int整数
    public void insert(int num) {
        IntTrie node = this;
        for (int i = 31; i >= 0; i--) {
            //从高位到低位获取每一位的值
            int bit = (num >> i) & 1;
            if (node.child[bit] == null) {
                node.child[bit] = new IntTrie();
            }
            node = node.child[bit];
        }
    }

    //num与树中的每个元素分别进行异或运算，返回其中最大的异或结果
    //在只考虑正数的情况下，越高位出现1，则值越大
    public int getMaxXor(int num) {
        int result = 0;
        IntTrie node = this;
        for (int i = 31; i >= 0; i--) {
            //从高位到低位获取每一位的值
            int bit = ((num >> i) & 1);
            //对该位取反
            int reverseBit = bit ^ 1;
            //该位存在对立的01，则可以异或得到一个1
            if (node.child[reverseBit] != null) {
                node = node.child[reverseBit];
                result |= 1 << i;
            } else {
                node = node.child[bit];
            }
        }
        return result;
    }

}
