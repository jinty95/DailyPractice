package test.cn.jinty.sql.code;

import cn.jinty.util.math.NumberUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import test.cn.jinty.sql.code.bo.IdRange;
import test.cn.jinty.sql.code.mapper.JobMapper;
import test.cn.jinty.sql.code.entity.Job;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        long id = 1L;
        Job job = jobMapper.selectById(id);
        System.out.printf("根据主键查询：id=%s, result=%s%n", id, job);
        System.out.println();

        List<Long> ids = Arrays.asList(1L, 2L);
        List<Job> jobList = jobMapper.selectByIds(ids);
        System.out.printf("根据主键批量查询：ids=%s%n", ids);
        jobList.forEach(System.out::println);
        System.out.println();

        Job param = new Job();
        param.setIsDeleted(0);
        param.setJobType("UPDATE_GOODS");
        jobList = jobMapper.selectByParam(param);
        System.out.println("根据条件查询：param=" + param);
        jobList.forEach(System.out::println);
        System.out.println();

        param = new Job();
        int count = jobMapper.selectCount(param);
        System.out.println("查询数量：count=" + count);
        int start = 0;
        int length = 5;
        jobList = jobMapper.selectByPage(param, start, length);
        System.out.printf("分页查询：start=%s, length=%s%n", start, length);
        jobList.forEach(System.out::println);
        System.out.println();

        int shardTotal = 4;
        int shardId = 3;
        jobList = jobMapper.selectByIdShard(new Job(), shardTotal, shardId);
        System.out.printf("ID分片查询：shardTotal=%s, shardId=%s%n", shardTotal, shardId);
        jobList.forEach(System.out::println);
        System.out.println();

        IdRange idRange = jobMapper.selectIdRange(new Job());
        System.out.println("查询ID范围：idRange=" + idRange);
        System.out.println();

        int n = 3;
        List<NumberUtil.NumberRange> numberRangeList = NumberUtil.splitToNGroup(new NumberUtil.NumberRange(idRange.getMinId(), idRange.getMaxId()), n);
        System.out.printf("将ID范围分为%d片：idRange=%s, idRanges=%s%n", n, idRange, numberRangeList);
        numberRangeList.forEach(numberRange -> {
            List<Job> list = jobMapper.selectByIdRange(new Job(), numberRange.getMin(), numberRange.getMax());
            System.out.println("根据ID范围查询：idRange=" + numberRange);
            list.forEach(System.out::println);
            System.out.println();
        });

    }

    // 注意：Insert/Update/Delete执行完都要commit，否则数据库不会受影响

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
        System.out.println("批量插入数据：effect=" + effect + ", ids=" + jobList.stream().map(Job::getId).collect(Collectors.toList()));

        sqlSession.commit();
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);

        Job job = jobMapper.selectById(1L);
        job.setUpdatedBy("me");
        int effect = jobMapper.updateById(job);
        System.out.println("更新数据：effect=" + effect);

        Job job1 = new Job();
        job1.setJobDesc("任务描述呀");
        Job param1 = new Job();
        int effect1 = jobMapper.updateByParam(job1, param1);
        System.out.println("更新数据：effect=" + effect1);

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
