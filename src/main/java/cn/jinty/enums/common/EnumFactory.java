package cn.jinty.enums.common;

import cn.jinty.entity.KeyValue;
import cn.jinty.util.object.ClassScanUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 枚举 - 工厂
 *
 * @author Jinty
 * @date 2021/12/7
 */
public final class EnumFactory {

    // 枚举类型 -> 枚举实例列表(键值对形式)
    private static final Map<String, List<KeyValue<String, String>>> ENUM_CONSTANTS_MAP = new HashMap<>();
    // 枚举类型 -> (枚举编码 -> 枚举实例)
    private static final Map<String, Map<String, EnumInterface<?>>> ENUM_CODE_OBJ_MAP = new HashMap<>();
    // 枚举类型 -> (枚举描述 -> 枚举实例)
    private static final Map<String, Map<String, EnumInterface<?>>> ENUM_DESC_OBJ_MAP = new HashMap<>();

    // 扫描enums包下面所有的枚举类
    static {
        String packageName = "cn.jinty.enums";
        try {
            Map<String, Class> classes = ClassScanUtil.scan(packageName);
            for (Class enumClass : classes.values()) {
                if (EnumInterface.class != enumClass && EnumInterface.class.isAssignableFrom(enumClass)) {
                    buildMap(enumClass.getSimpleName(), (EnumInterface<?>[]) enumClass.getEnumConstants());
                }
            }
        } catch (Exception e) {
            System.out.printf("扫描enums包下面所有的枚举类异常：packageName=%s, exception=%s, message=%s%n",
                    packageName, e.getClass().getName(), e.getMessage());
        }
    }

    /**
     * 构建枚举类型与枚举实例的映射
     *
     * @param type  枚举类型
     * @param value 枚举实例列表
     */
    private static void buildMap(String type, EnumInterface<?>[] value) {
        if (value == null) {
            return;
        }
        List<KeyValue<String, String>> list = new ArrayList<>();
        Map<String, EnumInterface<?>> codeMap = new HashMap<>();
        Map<String, EnumInterface<?>> descMap = new HashMap<>();
        for (EnumInterface<?> one : value) {
            list.add(new KeyValue<>(String.valueOf(one.getCode()), one.getDesc()));
            codeMap.put(String.valueOf(one.getCode()), one);
            descMap.put(one.getDesc(), one);
        }
        ENUM_CONSTANTS_MAP.put(type, list);
        ENUM_CODE_OBJ_MAP.put(type, codeMap);
        ENUM_DESC_OBJ_MAP.put(type, descMap);
    }

    /**
     * 根据枚举类型查询枚举实例列表
     *
     * @param type 枚举类型
     * @return 枚举实例列表(键值对形式)
     */
    public static List<KeyValue<String, String>> getByType(String type) {
        List<KeyValue<String, String>> list = ENUM_CONSTANTS_MAP.get(type);
        return list == null ? new ArrayList<>() : list;
    }

    /**
     * 枚举类型+枚举编码是否存在
     *
     * @param type 枚举类型
     * @param code 枚举编码
     * @param <T>  枚举编码类型
     * @return 是否
     */
    public static <T> boolean existTypeAndCode(String type, T code) {
        Map<String, EnumInterface<?>> codeObjMap = ENUM_CODE_OBJ_MAP.get(type);
        return codeObjMap != null && codeObjMap.containsKey(String.valueOf(code));
    }

    /**
     * 枚举类型+枚举描述是否存在
     *
     * @param type 枚举类型
     * @param desc 枚举描述
     * @return 是否
     */
    public static boolean existTypeAndDesc(String type, String desc) {
        Map<String, EnumInterface<?>> descObjMap = ENUM_DESC_OBJ_MAP.get(type);
        return descObjMap != null && descObjMap.containsKey(desc);
    }

    /**
     * 根据枚举类型+枚举编码查询枚举实例
     *
     * @param type 枚举类型
     * @param code 枚举编码
     * @param <T>  枚举编码类型
     * @return 枚举实例
     */
    @SuppressWarnings("unchecked")
    public static <T> EnumInterface<T> getByTypeAndCode(String type, T code) {
        Map<String, EnumInterface<?>> codeObjMap = ENUM_CODE_OBJ_MAP.get(type);
        return codeObjMap == null ? null : (EnumInterface<T>) codeObjMap.get(String.valueOf(code));
    }

    /**
     * 根据枚举类型+枚举描述查询枚举实例
     *
     * @param type 枚举类型
     * @param desc 枚举描述
     * @param <T>  枚举编码类型
     * @return 枚举实例
     */
    @SuppressWarnings("unchecked")
    public static <T> EnumInterface<T> getByTypeAndDesc(String type, String desc) {
        Map<String, EnumInterface<?>> descObjMap = ENUM_DESC_OBJ_MAP.get(type);
        return descObjMap == null ? null : (EnumInterface<T>) descObjMap.get(desc);
    }

    /**
     * 根据枚举类型+枚举编码获取对应的枚举描述
     *
     * @param type 枚举类型
     * @param code 枚举编码
     * @param <T>  枚举编码类型
     * @return 枚举描述
     */
    public static <T> String typeAndCodeToDesc(String type, T code) {
        EnumInterface<T> instance = getByTypeAndCode(type, code);
        return instance == null ? null : instance.getDesc();
    }

    /**
     * 根据枚举类型+枚举描述获取对应的枚举编码
     *
     * @param type 枚举类型
     * @param desc 枚举描述
     * @param <T>  枚举编码类型
     * @return 枚举编码
     */
    public static <T> T typeAndDescToCode(String type, String desc) {
        EnumInterface<T> instance = getByTypeAndDesc(type, desc);
        return instance == null ? null : instance.getCode();
    }

}
