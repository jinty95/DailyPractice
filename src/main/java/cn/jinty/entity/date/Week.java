package cn.jinty.entity.date;

import lombok.*;

import java.util.Date;

/**
 * 周、星期
 *
 * @author Jinty
 * @date 2021/11/10
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Week extends DateRange {

    // 序号 (年的第几周，从 1 开始)
    private int sequence;

    // 描述 (中文描述，从 第1周 开始)
    private String description;

    /**
     * 构造器
     *
     * @param sequence    序号
     * @param description 描述
     * @param begin       开始时间
     * @param end         结束时间
     */
    public Week(int sequence, String description, Date begin, Date end) {
        super(begin, end);
        this.sequence = sequence;
        this.description = description;
    }

}
