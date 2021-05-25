package juc.delay;

import cn.jinty.util.DateUtil;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 订单 - 具有延时取消功能
 *
 * @author jinty
 * @date 2021/5/25
 **/
public class Order implements Delayed {

    //ID
    private int id;
    //创建时间
    private long createTime;
    //取消时间
    private long cancelTime;

    //创建一个订单，在指定时间后取消
    public Order(int id, long time, TimeUnit timeUnit) {
        this.id = id;
        this.createTime = System.currentTimeMillis();
        this.cancelTime = System.currentTimeMillis() + (time > 0 ? timeUnit.toMillis(time) : 0);
    }

    //打印对象属性
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createTime=" + DateUtil.YYYY_MM_DD_HH_MM_SS.format(new Date(createTime)) +
                ", cancelTime=" + DateUtil.YYYY_MM_DD_HH_MM_SS.format(new Date(cancelTime)) +
                '}';
    }

    //获取剩余延时时间
    @Override
    public long getDelay(TimeUnit unit) {
        return cancelTime - System.currentTimeMillis();
    }

    //按照剩余延时时间排序
    @Override
    public int compareTo(Delayed o) {
        Order Order = (Order) o;
        long diff = this.cancelTime - Order.cancelTime;
        if (diff < 0) {
            return -1;
        } else if (diff > 0) {
            return 1;
        } else {
            return 0;
        }
    }

}
