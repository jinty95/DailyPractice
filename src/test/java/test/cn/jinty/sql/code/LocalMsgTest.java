package test.cn.jinty.sql.code;

import cn.jinty.util.collection.CollectionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import test.cn.jinty.sql.code.entity.LocalMsg;
import test.cn.jinty.sql.code.mapper.ext.LocalMsgMapperExt;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 本地消息 - 处理逻辑
 *
 * @author Jinty
 * @date 2025/5/10
 */
public class LocalMsgTest {

    private static SqlSessionFactory sqlSessionFactory;
    private static final String MYBATIS_CONFIG_XML = "config/mybatis-config.xml";

    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream(MYBATIS_CONFIG_XML);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LocalMsgMapperExt mapperExt = sqlSession.getMapper(LocalMsgMapperExt.class);

        // 删除数据
        int deleteCnt = mapperExt.deleteAll();
        sqlSession.commit();
        System.out.println("清空数据：" + deleteCnt);

        // 新增数据
        List<LocalMsg> msgList = genMsg();
        mapperExt.batchInsert(msgList);
        sqlSession.commit();
        System.out.println("新增数据：" + msgList.size());

        // 正常处理
        List<LocalMsg> normalList = mapperExt.listNew(1, 10);
        int normalSize = CollectionUtil.size(normalList);
        int normalSuccess = 0;
        while (CollectionUtil.isNotEmpty(normalList)) {
            for (LocalMsg localMsg : normalList) {
                // TODO 执行业务处理

                // 更新为成功
                localMsg.setMsgStatus(1);
                localMsg.setFirstConsumeTime(new Date());
                localMsg.setUpdateTime(new Date());
                mapperExt.updateById(localMsg);
                sqlSession.commit();
                normalSuccess++;
            }
            normalList = mapperExt.listNew(1, 10);
            normalSize += CollectionUtil.size(normalList);
        }
        System.out.printf("正常消息处理：正常消息数=%d，处理成功数=%d%n", normalSize, normalSuccess);

        // 异常处理
        List<LocalMsg> failList = mapperExt.listFail(1, 10, 10);
        int failCnt = 0;
        while (CollectionUtil.isNotEmpty(failList)) {
            for (LocalMsg localMsg : failList) {
                // TODO 执行业务处理

                // 更新为失败
                localMsg.setMsgStatus(2);
                localMsg.setLastConsumeTime(new Date());
                localMsg.setMsgErrInfo("数据异常，处理失败");
                localMsg.setMsgRetry(localMsg.getMsgRetry() + 1);
                localMsg.setUpdateTime(new Date());
                mapperExt.updateById(localMsg);
                sqlSession.commit();
                failCnt++;
            }
            failList = mapperExt.listFail(1, 10, 10);
        }
        System.out.printf("失败消息处理：失败次数=%d%n", failCnt);
    }

    private List<LocalMsg> genMsg() {
        List<LocalMsg> list = new ArrayList<>();
        LocalMsg msg1 = new LocalMsg();
        msg1.setMsgType(1);
        msg1.setMsgContent("{\"orderNo\":\"10086\"}");
        list.add(msg1);
        LocalMsg msg2 = new LocalMsg();
        msg2.setMsgType(1);
        msg2.setMsgContent("{\"orderNo\":\"10087\"}");
        msg2.setMsgStatus(2);
        msg2.setMsgErrInfo("系统异常");
        list.add(msg2);
        LocalMsg msg3 = new LocalMsg();
        msg3.setMsgType(1);
        msg3.setMsgContent("{\"orderNo\":\"10088\"}");
        msg3.setMsgStatus(2);
        msg3.setMsgErrInfo("系统异常");
        list.add(msg3);
        return list;
    }

}
