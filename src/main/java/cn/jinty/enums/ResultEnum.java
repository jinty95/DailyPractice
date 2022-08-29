package cn.jinty.enums;

import cn.jinty.enums.common.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举 - 结果
 *
 * @author Jinty
 * @date 2021/12/10
 **/
@Getter
@AllArgsConstructor
public enum ResultEnum implements EnumInterface<String> {

    SUCCESS("0", "成功"),
    FAIL("1", "失败"),
    ;

    // 编码
    private final String code;
    // 描述
    private final String desc;

}
