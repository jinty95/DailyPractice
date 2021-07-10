package cn.jinty.leetcode.entity;

import org.junit.Test;

/**
 * 基于时间的键值存储 - 测试
 *
 * @author jinty
 * @date 2021/7/10
 **/
public class TimeMapTest {

    @Test
    public void test(){
        TimeMap map = new TimeMap();
        map.set("1","1",1);
        map.set("1","2",2);
        map.set("1","3",3);
        System.out.println(map.get("1",0));
        System.out.println(map.get("1",1));
        System.out.println(map.get("1",3));
    }

}
