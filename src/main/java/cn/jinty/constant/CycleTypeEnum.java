package cn.jinty.constant;

import cn.jinty.constant.common.EnumInterface;

/**
 * 枚举 - 周期类型
 *
 * @author jintai.wang
 * @date 2022/3/17
 **/
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

    CycleTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

}
