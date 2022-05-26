package test.cn.jinty.annotation.cache;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务 - 实现
 *
 * @author Jinty
 * @date 2022/5/26
 **/
public class ServiceImpl implements Service {

    @Override
    public List<String> listByType(String type) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            result.add(type + '-' + i);
        }
        System.out.println("listByType已执行：type=" + type + ", result=" + result);
        return result;
    }

    @Override
    public String getById(Long id) {
        String result = String.valueOf(id);
        System.out.println("getById已执行：id=" + id + ", result=" + result);
        return result;
    }

}
