package cn.jinty.leetcode.entity;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 座位预约管理系统
 * 设计一个管理 n 个座位预约的系统，座位编号从 1 到 n 。
 *
 * @author Jinty
 * @date 2021/11/24
 **/
public class SeatManager {

    // 可预约的座位
    private final PriorityQueue<Integer> canReserve;
    // 已被预约的座位
    private final Set<Integer> reserve;

    // 初始化一个 SeatManager 对象，它管理从 1 到 n 编号的 n 个座位。所有座位初始都是可预约的。
    public SeatManager(int n) {
        canReserve = new PriorityQueue<>(n);
        reserve = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            canReserve.offer(i);
        }
    }

    // 返回可以预约座位的 最小编号 ，此座位变为不可预约。
    public int reserve() {
        if (canReserve.isEmpty()) {
            return -1;
        }
        int seatNumber = canReserve.poll();
        reserve.add(seatNumber);
        return seatNumber;
    }

    // 将给定编号 seatNumber 对应的座位变成可以预约。
    public void unreserve(int seatNumber) {
        if (reserve.contains(seatNumber)) {
            reserve.remove(seatNumber);
            canReserve.offer(seatNumber);
        }
    }

}
