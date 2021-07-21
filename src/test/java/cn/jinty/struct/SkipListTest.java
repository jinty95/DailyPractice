package cn.jinty.struct;

import org.junit.Test;

import java.util.List;

/**
 * 跳跃表 - 测试
 *
 * @author jinty
 * @date 2021/7/21
 **/
public class SkipListTest {

    @Test
    public void test(){
        SkipList skipList = new SkipList();
        for(int i=1; i<=100; i++){
            skipList.insert(i);
        }
        List<List<Integer>> lists = skipList.showList();
        for(List<Integer> list : lists){
            System.out.println(list);
        }
        System.out.println(skipList.search(50));
        skipList.delete(50);
        System.out.println(skipList.search(50));
    }

}
