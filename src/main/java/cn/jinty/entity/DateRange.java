package cn.jinty.entity;

import cn.jinty.util.DateUtil;

import java.util.Date;

/**
 * 时间范围 - 实体类
 *
 * @author jintai.wang
 * @date 2021/11/3
 **/
public class DateRange {

    // 起始时间
    private Date begin;

    // 结束时间
    private Date end;

    public DateRange() {}

    public DateRange(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "DateRange{" +
                "begin=" + DateUtil.format(begin) +
                ", end=" + DateUtil.format(end) +
                '}';
    }

}
