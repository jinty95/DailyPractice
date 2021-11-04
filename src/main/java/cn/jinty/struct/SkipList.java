package cn.jinty.struct;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 跳跃表 - 基于多层索引实现快速检索的有序链表
 *
 * @author Jinty
 * @date 2021/7/21
 **/
public class SkipList {

    //内部类：链表节点
    public static class SkipNode {

        //节点值
        private final int value;
        //后继指针数组
        private final SkipNode[] next = new SkipNode[MAX_LEVEL];
        //节点最大索引层级
        private final int maxLevel;

        public SkipNode(int value, int maxLevel) {
            this.value = value;
            this.maxLevel = maxLevel;
        }

        @Override
        public String toString() {
            return "{value: " + value + "; level: " + maxLevel + "}";
        }

    }

    //最高索引层级
    private static final int MAX_LEVEL = 8;
    //当前跳表最高层级
    private int curMaxLevel = 0;
    //头节点
    private final SkipNode head = new SkipNode(Integer.MIN_VALUE, 0);
    //随机
    private final Random random = new Random();

    //随机生成当前节点的层级
    private int randomLevel() {
        int level = 1;
        //每层都有50%的概率出现
        for (int i = 1; i < MAX_LEVEL; ++i) {
            if (random.nextInt(2) == 1) {
                level++;
            }
        }
        return level;
    }

    //查询
    public SkipNode search(int value) {
        SkipNode temp = head;
        //下沉
        for (int i = curMaxLevel - 1; i >= 0; i--) {
            //右移
            while (temp.next[i] != null && temp.next[i].value < value) {
                temp = temp.next[i];
            }
        }
        if (temp.next[0] != null && temp.next[0].value == value) {
            return temp.next[0];
        }
        return null;
    }

    //插入
    public void insert(int value) {
        //随机层级
        int maxLevel = randomLevel();
        SkipNode newNode = new SkipNode(value, maxLevel);
        //从head开始寻找当前节点各层的直接前驱
        SkipNode[] pre = new SkipNode[maxLevel];
        SkipNode temp = head;
        for (int i = maxLevel - 1; i >= 0; i--) {
            while (temp.next[i] != null && temp.next[i].value < value) {
                temp = temp.next[i];
            }
            pre[i] = temp;
        }
        //从前驱和后继中间插入当前节点
        for (int i = 0; i < maxLevel; i++) {
            newNode.next[i] = pre[i].next[i];
            pre[i].next[i] = newNode;
        }
        //更新跳表的当前最高层级
        curMaxLevel = Math.max(curMaxLevel, maxLevel);
    }

    //删除
    public void delete(int value) {
        //从head开始寻找value节点各层的直接前驱
        SkipNode[] pre = new SkipNode[curMaxLevel];
        SkipNode temp = head;
        for (int i = curMaxLevel - 1; i >= 0; i--) {
            while (temp.next[i] != null && temp.next[i].value < value) {
                temp = temp.next[i];
            }
            pre[i] = temp;
        }
        //存在值为value的节点，则删除这个节点，并连接它的直接前驱与后继
        if (temp.next[0] != null && temp.next[0].value == value) {
            for (int i = curMaxLevel - 1; i >= 0; i--) {
                if (pre[i].next[i] != null && pre[i].next[i].value == value) {
                    pre[i].next[i] = pre[i].next[i].next[i];
                }
            }
        }
    }

    //展示跳表结构
    public List<List<Integer>> showList() {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = curMaxLevel - 1; i >= 0; i--) {
            List<Integer> list = new ArrayList<>();
            SkipNode temp = head.next[i];
            while (temp != null) {
                list.add(temp.value);
                temp = temp.next[i];
            }
            lists.add(list);
        }
        return lists;
    }

}
