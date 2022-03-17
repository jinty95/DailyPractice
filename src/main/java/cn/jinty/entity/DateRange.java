package cn.jinty.entity;

import cn.jinty.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 时间范围
 *
 * @author Jinty
 * @date 2021/11/3
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateRange {

    // 起始时间
    private Date begin;

    // 结束时间
    private Date end;

    @Override
    public String toString() {
        return "DateRange{" +
                "begin=" + DateUtil.format(begin) +
                ", end=" + DateUtil.format(end) +
                '}';
    }

}
