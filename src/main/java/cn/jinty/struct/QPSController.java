package cn.jinty.struct;

/**
 * QPS控制器
 * 使用数组存储每个请求到来的时间，前 N 次请求顺利通过，并填满数组。
 * 后续请求到来时，判断当前时间是否比数组中最早的时间晚 1 个时间单位，是则通过，否则拒绝。
 *
 * @author Jinty
 * @date 2022/6/28
 **/
public class QPSController {

    // 单位时间请求限制次数
    private final int limit;

    // 时间单位(毫秒表示)
    private final long time;

    // 存储请求时间的容器
    private final long[] container;

    // 指针，标识容器中的下一个可用位置
    private int position = 0;

    /**
     * 构造器
     *
     * @param limit 单位时间请求限制次数
     * @param time  时间单位(毫秒表示)
     */
    public QPSController(int limit, long time) {
        this.limit = limit;
        this.time = time;
        this.container = new long[limit];
    }

    /**
     * 请求占用容器的下一个位置
     *
     * @return 是否占用成功
     */
    public synchronized boolean next() {
        long cur = System.currentTimeMillis();
        if (cur >= container[position] + time) {
            container[position] = cur;
            position = (position + 1) % limit;
            return true;
        }
        return false;
    }

    /**
     * 当占位失败时，计算最短等待时间
     *
     * @return 等待时间(毫秒表示)
     */
    public long waitTime() {
        return container[position] + time - System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return limit + "/" + time + "ms";
    }

}
