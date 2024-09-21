package test.cn.jinty.sql.code.mapper;

import org.apache.ibatis.annotations.Param;
import test.cn.jinty.sql.code.entity.Job;
import test.cn.jinty.sql.code.bo.IdRange;

import java.util.List;

/**
 * 作业表 - Mapper
 *
 * @author Jinty
 * @date 2024/09/21
 */
public interface JobMapper {

    /**
     * 插入(字段都填充默认值)
     */
    int insertDefault();

    /**
     * 插入(忽略空字段)
     */
    int insert(@Param("item") Job item);

    /**
     * 批量插入(空字段填充默认值)
     */
    int batchInsert(@Param("list") List<Job> list);

    /**
     * 根据主键更新(忽略空字段)
     * 注意：更新语句中包含了update_time字段，会导致ON UPDATE CURRENT_TIMESTAMP失效，使用时需要手动赋予当前时间
     */
    int updateById(@Param("item") Job item);

    /**
     * 根据条件更新(忽略空字段)
     */
    int updateByParam(@Param("item") Job item, @Param("param") Job param);

    /**
     * 根据主键批量逻辑删除
     */
    int logicDeleteByIds(@Param("ids") List<Long> ids, @Param("updatedBy") String updatedBy);

    /**
     * 根据主键删除
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据主键批量删除
     */
    int deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 根据主键查询
     */
    Job selectById(@Param("id") Long id);

    /**
     * 根据主键批量查询
     */
    List<Job> selectByIds(@Param("ids") List<Long> ids);

    /**
     * 查询(条件忽略空字段)
     */
    List<Job> selectByParam(@Param("param") Job param);

    /**
     * 查询数量(条件忽略空字段)
     */
    int selectCount(@Param("param") Job param);

    /**
     * 分页查询(条件忽略空字段)
     */
    List<Job> selectByPage(@Param("param") Job param, @Param("start") Integer start, @Param("length") Integer length);

    /**
     * ID分片查询(条件忽略空字段)
     */
    List<Job> selectByIdShard(@Param("param") Job param, @Param("shardTotal") Integer shardTotal, @Param("shardId") Integer shardId);

    /**
     * 查询ID范围(条件忽略空字段)
     */
    IdRange selectIdRange(@Param("param") Job param);

    /**
     * 根据ID范围进行查询(条件忽略空字段)
     */
    List<Job> selectByIdRange(@Param("param") Job param, @Param("minId") Long minId, @Param("maxId") Long maxId);

}