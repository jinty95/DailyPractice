package test.cn.jinty.config;

import cn.jinty.config.Config;
import cn.jinty.config.ConfigService;
import cn.jinty.util.string.StringUtil;
import cn.jinty.util.collection.CollectionUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 配置 - ServiceImpl
 *
 * @author Jinty
 * @date 2023/1/17
 **/
public class ConfigServiceImpl implements ConfigService {

    @Override
    public Config get(String type, String key) {
        return new Config(type, key, "xxx", "xxx", "xxx");
    }

    @Override
    public String getValue(String type, String key) {
        return "xxx";
    }

    @Override
    public String getValueOrDefault(String type, String key, String defaultValue) {
        String value = getValue(type, key);
        return StringUtil.isEmpty(value) ? defaultValue : value;
    }

    @Override
    public List<Config> list(String type) {
        return Arrays.asList(
                new Config(type, "xxx", "xxx", "xxx", "xxx"),
                new Config(type, "yyy", "yyy", "yyy", "yyy")
        );
    }

    @Override
    public List<String> listKey(String type) {
        return Arrays.asList("xxx", "yyy");
    }

    @Override
    public List<String> listKeyOrDefault(String type, List<String> defaultKeys) {
        List<String> keys = listKey(type);
        return CollectionUtil.isEmpty(keys) ? defaultKeys : keys;
    }

}
