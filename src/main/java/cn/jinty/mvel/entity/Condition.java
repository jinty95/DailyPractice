package cn.jinty.mvel.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 条件
 *
 * @author Jinty
 * @date 2023/9/6
 **/
@Data
@NoArgsConstructor
public class Condition {

    // 操作符类型
    private String operatorType;

    // 操作符
    private String operator;

    // 指标名
    private String metricName;

    // 指标值
    private String metricValue;

    // 逻辑操作符的子条件列表
    private List<Condition> children;

}
