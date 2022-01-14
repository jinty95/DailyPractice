package cn.jinty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 时间范围 - 实体类
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

}
