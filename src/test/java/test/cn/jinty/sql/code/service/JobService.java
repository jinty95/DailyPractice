package test.cn.jinty.sql.code.service;

import test.cn.jinty.sql.code.entity.Job;

import java.util.List;

/**
 * 作业表 - Service
 *
 * @author Jinty
 * @date 2024/09/21
 */
public interface JobService {

    /**
     * 插入(忽略空字段)
     */
    boolean insert(Job item);

    /**
     * 批量插入(空字段填充默认值)
     */
    boolean batchInsert(List<Job> list);

    /**
     * 根据主键更新(忽略空字段)
     */
    boolean updateById(Job item);

    /**
     * 根据条件更新(忽略空字段)
     */
    boolean updateByParam(Job item, Job param);

    /**
     * 根据主键批量逻辑删除
     */
    int logicDeleteByIds(List<Long> ids, String updatedBy);

    /**
     * 根据主键查询
     */
    Job selectById(Long id);

    /**
     * 根据主键批量查询
     */
    List<Job> selectByIds(List<Long> ids);

    /**
     * 查询(条件忽略空字段)
     */
    List<Job> selectByParam(Job param);

    /**
     * 查询数量(条件忽略空字段)
     */
    int selectCount(Job param);

    /**
     * 分页查询(条件忽略空字段)
     */
    List<Job> selectByPage(Job param, Integer start, Integer length);

}