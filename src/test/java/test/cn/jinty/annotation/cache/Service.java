package test.cn.jinty.annotation.cache;

import cn.jinty.annotation.Cache;

import java.util.List;

/**
 * 服务 - 接口
 *
 * @author Jinty
 * @date 2022/5/26
 **/
public interface Service {

    @Cache(key = "Service.listByType", expireTime = 2000L)
    List<String> listByType(String type);

    @Cache(key = "Service.getById")
    String getById(Long id);

}
