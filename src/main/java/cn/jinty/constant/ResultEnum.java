package cn.jinty.constant;

import cn.jinty.constant.common.EnumInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 - 结果
 *
 * @author Jinty
 * @date 2021/12/10
 **/
public enum ResultEnum implements EnumInterface {

    SUCCESS("SUCCESS", "成功"),
    FAIL("FAIL", "失败");

    // 编码
    private final String code;
    // 描述
    private final String desc;

    // 构造器
    ResultEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    // getter
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

}
