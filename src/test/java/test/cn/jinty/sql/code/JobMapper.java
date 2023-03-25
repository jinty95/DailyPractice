package test.cn.jinty.sql.code;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 作业表 - Mapper
 *
 * @author Jinty
 * @date 2023/03/25
 */
public interface JobMapper {

    /**
     * 插入数据(字段都填充默认值)
     */
    int insertDefault();

    /**
     * 插入数据(忽略空字段)
     */
    int insert(Job item);

    /**
     * 批量插入数据(空字段填充默认值)
     */
    int batchInsert(@Param("list") List<Job> list);

    /**
     * 根据主键更新数据(忽略空字段)
     */
    int updateByPk(Job item);

    /**
     * 根据主键删除数据
     */
    int deleteByPk(Long id);

    /**
     * 根据主键查询数据
     */
    Job selectByPk(Long id);

    /**
     * 查询数据(条件忽略空字段)
     */
    List<Job> select(Job item);

}