package cn.jinty.enums;

import cn.jinty.enums.common.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举 - 周期类型
 *
 * @author Jinty
 * @date 2022/3/17
 **/
@Getter
@AllArgsConstructor
public enum CycleTypeEnum implements EnumInterface<Integer> {

    DAY(1, "日"),
    WEEK(2, "周"),
    MONTH(3, "月"),
    YEAR(4, "年"),
    ;

    // 编码
    private final Integer code;
    // 描述
    private final String desc;

}
