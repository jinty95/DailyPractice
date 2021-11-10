package cn.jinty.entity;

import cn.jinty.util.DateUtil;

import java.util.Date;

/**
 * 周、星期 - 实体类
 *
 * @author Jinty
 * @date 2021/11/10
 **/
public class Week extends DateRange {

    // 序号 (年的第几周，从 1 开始)
    private int sequence;

    // 描述 (中文描述，从 第1周 开始)
    private String description;

    public Week() {
    }

    public Week(int sequence, String description, Date begin, Date end) {
        super(begin, end);
        this.sequence = sequence;
        this.description = description;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Week{" +
                "sequence=" + sequence +
                ", description=" + description +
                ", begin=" + DateUtil.format(getBegin()) +
                ", end=" + DateUtil.format(getEnd()) +
                '}';
    }

}
