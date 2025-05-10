package test.cn.jinty.sql.code.mapper.ext;

import org.apache.ibatis.annotations.Param;
import test.cn.jinty.sql.code.entity.LocalMsg;
import test.cn.jinty.sql.code.mapper.LocalMsgMapper;

import java.util.List;

/**
 * 本地消息表 - MapperExt
 * 不要修改生成的Mapper，继承生成的Mapper，在MapperExt写个性化SQL
 *
 * @author Jinty
 * @date 2025/05/10
 */
public interface LocalMsgMapperExt extends LocalMsgMapper {

    // 查询正常消息
    List<LocalMsg> listNew(@Param("msgType") int msgType,
                           @Param("limit") int limit);

    // 查询失败消息
    List<LocalMsg> listFail(@Param("msgType") int msgType,
                            @Param("msgRetry") int msgRetry,
                            @Param("limit") int limit);

    // 删除所有数据
    int deleteAll();
}
