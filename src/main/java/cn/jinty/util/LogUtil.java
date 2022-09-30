package cn.jinty.util;

import cn.jinty.enums.EntityEnum;
import cn.jinty.enums.OperationEnum;
import cn.jinty.enums.ResponseCodeEnum;

/**
 * 日志 - 工具类
 *
 * @author Jinty
 * @date 2021/12/10
 **/
public final class LogUtil {

    /**
     * 标准格式 - 打印日志
     *
     * @param entity    实体
     * @param operation 操作
     * @param result    结果
     * @return 字符串
     */
    public static String standardFormat(EntityEnum entity, OperationEnum operation, ResponseCodeEnum result) {
        return "[" + entity.getDesc() + "-"
                + operation.getDesc() + "-"
                + result.getDesc() + "]";
    }

    /**
     * 简单格式 - 返回提示语
     *
     * @param entity    实体
     * @param operation 操作
     * @param result    结果
     * @return 字符串
     */
    public static String simpleFormat(EntityEnum entity, OperationEnum operation, ResponseCodeEnum result) {
        return operation.getDesc() + entity.getDesc() + result.getDesc();
    }

}
