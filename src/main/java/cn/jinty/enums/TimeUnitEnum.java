package cn.jinty.enums;

import cn.jinty.enums.common.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举 - 时间单位
 *
 * @author Jinty
 * @date 2023/4/18
 **/
@Getter
@AllArgsConstructor
public enum TimeUnitEnum implements EnumInterface<String> {

    MILLISECOND("ms", "毫秒", 1L),
    SECOND("s", "秒", 1000L * MILLISECOND.millis),
    MINUTE("min", "分", 60L * SECOND.millis),
    HOUR("h", "时", 60L * MINUTE.millis),
    DAY("d", "日", 24L * HOUR.millis),
    WEEK("w", "周", 7L * DAY.millis),
    MONTH("m", "月", 30L * DAY.millis),
    YEAR("y", "年", 365L * DAY.millis),
    ;

    // 编码
    private final String code;
    // 描述
    private final String desc;
    // 毫秒数
    private final long millis;

    /**
     * 获取n个时间单位对应的毫秒数
     * 例如：6s = 6000ms
     *
     * @param n 时间单位个数
     * @return 毫秒数
     */
    public long toMillis(long n) {
        return n * this.millis;
    }

    /**
     * 获取毫秒数对应的时间单位个数
     * 例如：6000ms = 6s
     *
     * @param millis 毫秒数
     * @return 时间单位个数
     */
    public long fromMills(long millis) {
        return millis / this.millis;
    }

}
