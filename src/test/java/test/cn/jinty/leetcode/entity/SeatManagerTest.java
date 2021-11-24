package test.cn.jinty.leetcode.entity;

import cn.jinty.leetcode.entity.SeatManager;
import org.junit.Test;

/**
 * 座位预约管理系统 - 测试
 *
 * @author Jinty
 * @date 2021/11/24
 **/
public class SeatManagerTest {

    @Test
    public void test() {
        SeatManager sm = new SeatManager(10);
        System.out.println(sm.reserve());
        System.out.println(sm.reserve());
        sm.unreserve(2);
        System.out.println(sm.reserve());
        System.out.println(sm.reserve());
        System.out.println(sm.reserve());
        sm.unreserve(2);
        sm.unreserve(2);
        sm.unreserve(2);
        System.out.println(sm.reserve());
        System.out.println(sm.reserve());
    }

}
