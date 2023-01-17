package test.cn.jinty.config;

import cn.jinty.config.Config;
import cn.jinty.config.ConfigService;

import java.util.ArrayList;
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
        return defaultValue;
    }

    @Override
    public List<Config> list(String type) {
        return new ArrayList<>();
    }

}
