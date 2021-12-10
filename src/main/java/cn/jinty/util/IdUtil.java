package cn.jinty.util;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ID - 工具类
 *
 * @author Jinty
 * @date 2021/12/9
 **/
public final class IdUtil {

    private static final AtomicLong number = new AtomicLong(0);

    private static final AtomicLong timestamp = new AtomicLong(0);

    /**
     * ID - UUID(无序)
     *
     * @return UUID
     */
    public static String uuid() {
        // 固定格式：8位-4位-4位-4位-12位 ("-"可以去除，不会影响唯一性)
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * ID - 数字(严格递增)
     *
     * @return 数字
     */
    public static long number() {
        return number.addAndGet(1);
    }

    /**
     * ID - 时间戳(严格递增)
     *
     * @return 时间戳
     */
    public static long timestamp() {
        while (true) {
            long now = System.currentTimeMillis();
            long before = timestamp.get();
            if (now > before) {
                if (timestamp.compareAndSet(before, now)) {
                    return now;
                }
            }
        }
    }

    /**
     * ID - 时间加随机数(趋势递增)
     *
     * @return 时间加随机数
     */
    public static String timeAndRandom() {
        // 17位时间
        String date = DateUtil.format(new Date(), DateUtil.COMPACT_WHOLE);
        // 5位随机数
        String random = StringUtil.randomDigit(5);
        return date + random;
    }

}
