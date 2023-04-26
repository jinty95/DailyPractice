package cn.jinty.config;

import java.util.List;

/**
 * 配置 - Service
 *
 * @author Jinty
 * @date 2023/1/17
 **/
public interface ConfigService {

    /**
     * 根据 "类型+键" 查询 "配置"
     *
     * @param type 类型
     * @param key  键
     * @return 配置
     */
    Config get(String type, String key);

    /**
     * 根据 "类型+键" 查询 "值"
     *
     * @param type 类型
     * @param key  键
     * @return 值
     */
    String getValue(String type, String key);

    /**
     * 根据 "类型+键" 查询 "值"，不存在时返回 "默认值"
     *
     * @param type         类型
     * @param key          键
     * @param defaultValue 默认值
     * @return 值
     */
    String getValueOrDefault(String type, String key, String defaultValue);

    /**
     * 根据 "类型" 查询 "配置列表"
     *
     * @param type 类型
     * @return 配置列表
     */
    List<Config> list(String type);

    /**
     * 根据 "类型" 查询 "键列表"
     *
     * @param type 类型
     * @return 键列表
     */
    List<String> listKey(String type);

    /**
     * 根据 "类型" 查询 "键列表"，不存在时返回 "默认值"
     *
     * @param type        类型
     * @param defaultKeys 默认值
     * @return 键列表
     */
    List<String> listKeyOrDefault(String type, List<String> defaultKeys);

}
