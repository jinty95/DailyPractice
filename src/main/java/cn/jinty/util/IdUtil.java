package cn.jinty.util;

import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ID - 工具类
 *
 * @author Jinty
 * @date 2021/12/9
 **/
public final class IdUtil {

    private static final AtomicLong NUMBER = new AtomicLong(0);

    private static final Map<String, AtomicLong> PREFIX_NUMBER_MAP = new HashMap<>();

    private static final AtomicLong TIMESTAMP = new AtomicLong(0);

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
        return NUMBER.addAndGet(1);
    }

    /**
     * ID - 前缀 + 数字(严格递增)
     *
     * @param prefix 前缀
     * @return 前缀 + 数字
     */
    public static String number(String prefix) {
        return prefix + PREFIX_NUMBER_MAP.computeIfAbsent(prefix, a -> new AtomicLong(0)).addAndGet(1);
    }

    /**
     * ID - 前缀 + 数字(严格递增、等长)
     *
     * @param prefix 前缀
     * @param length 数字长度
     * @return 前缀 + 数字
     */
    public static String number(String prefix, int length) {
        long number = PREFIX_NUMBER_MAP.computeIfAbsent(prefix, a -> new AtomicLong(0)).addAndGet(1);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMinimumIntegerDigits(length);
        nf.setMaximumIntegerDigits(length);
        if (String.valueOf(number).length() > length) {
            throw new RuntimeException("自增数字已达上限" + (int) Math.pow(10, length));
        }
        return prefix + nf.format(number);
    }

    /**
     * ID - 时间戳(严格递增)
     *
     * @return 时间戳
     */
    public static long timestamp() {
        while (true) {
            long now = System.currentTimeMillis();
            long before = TIMESTAMP.get();
            if (now > before) {
                if (TIMESTAMP.compareAndSet(before, now)) {
                    return now;
                }
            }
        }
    }

    /**
     * ID - 带随机数的时间(趋势递增)
     *
     * @return 时间 + 随机数
     */
    public static String timeWithRandom() {
        // 17位时间
        String date = DateUtil.format(new Date(), DateUtil.DATETIME_MILLI_2);
        // 5位随机数
        String random = StringUtil.randomDigit(5);
        return date + random;
    }

}
