package test.cn.jinty.sql.code;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis - 测试
 *
 * @author Jinty
 * @date 2023/3/25
 */
public class MybatisTest {

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
    public void testSelect() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
        Job job = jobMapper.selectByPk(1L);
        System.out.println("根据主键查询：result=" + job);
        Job param = new Job();
        param.setIsDeleted(0);
        param.setJobType("UPDATE_GOODS");
        List<Job> jobList = jobMapper.select(param);
        System.out.println("根据条件查询：param=" + param);
        System.out.println("result=" + jobList);
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
        Job job = jobMapper.selectByPk(1L);
        job.setUpdatedBy("me");
        int effect = jobMapper.updateByPk(job);
        System.out.println("更新数据：effect=" + effect);
        // 不加commit数据库不会受影响
        sqlSession.commit();
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
        int effect = jobMapper.insertDefault();
        System.out.println("插入数据：effect=" + effect);
        Job job = new Job();
        job.setJobDesc("hello world");
        effect = jobMapper.insert(job);
        System.out.println("插入数据：effect=" + effect + ", id=" + job.getId());
        List<Job> jobList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Job a = new Job();
            a.setJobDesc("hello " + i);
            jobList.add(a);
        }
        effect = jobMapper.batchInsert(jobList);
        System.out.println("批量插入数据：effect=" + effect);
        // 不加commit数据库不会受影响
        sqlSession.commit();
    }

    @Test
    public void testDelete() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
        Long id = 10L;
        int effect = jobMapper.deleteByPk(id);
        System.out.println("删除数据：effect=" + effect + ", id=" + id);
        // 不加commit数据库不会受影响
        sqlSession.commit();
    }

}
