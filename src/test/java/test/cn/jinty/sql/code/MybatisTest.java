package test.cn.jinty.sql.code;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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

        Job job = jobMapper.selectById(1L);
        System.out.println("根据主键查询：result=" + job);

        List<Job> jobList = jobMapper.selectByIds(Arrays.asList(1L, 2L));
        System.out.println("根据主键批量查询：result=" + jobList);

        Job param = new Job();
        param.setIsDeleted(0);
        param.setJobType("UPDATE_GOODS");
        jobList = jobMapper.select(param);
        System.out.println("根据条件查询：param=" + param);
        System.out.println("result=" + jobList);
    }

    // 注意：Update/Insert/Delete执行完都要commit，否则数据库不会受影响

    @Test
    public void testUpdate() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);

        Job job = jobMapper.selectById(1L);
        job.setUpdatedBy("me");
        int effect = jobMapper.updateById(job);
        System.out.println("更新数据：effect=" + effect);

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

        sqlSession.commit();
    }

    @Test
    public void testDelete() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);

        Long id = 10L;
        int effect = jobMapper.deleteById(id);
        System.out.println("删除数据：effect=" + effect + ", id=" + id);
        sqlSession.commit();

        List<Long> ids = Arrays.asList(10L, 11L, 12L);
        effect = jobMapper.deleteByIds(ids);
        System.out.println("批量删除数据：effect=" + effect + ", ids=" + ids);
        sqlSession.commit();
    }

}
