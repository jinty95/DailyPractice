package cn.jinty.enums.common;

/**
 * 枚举 - 接口
 *
 * @author Jinty
 * @date 2021/12/7
 */
public interface EnumInterface<T> {

    /**
     * 获取编码 (类型可以是String、Integer、Byte等)
     *
     * @return 编码
     */
    T getCode();

    /**
     * 获取描述
     *
     * @return 描述
     */
    String getDesc();

}
