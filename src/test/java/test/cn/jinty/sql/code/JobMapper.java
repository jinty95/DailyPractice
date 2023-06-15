package test.cn.jinty.sql.code;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 作业表 - Mapper
 *
 * @author Jinty
 * @date 2023/06/15
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
    int updateById(Job item);

    /**
     * 根据主键删除数据
     */
    int deleteById(Long id);

    /**
     * 根据主键批量删除数据
     */
    int deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 根据主键查询数据
     */
    Job selectById(Long id);

    /**
     * 根据主键批量查询数据
     */
    List<Job> selectByIds(@Param("ids") List<Long> ids);

    /**
     * 查询数据(条件忽略空字段)
     */
    List<Job> select(Job param);

}