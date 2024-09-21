package test.cn.jinty.sql.code.service.impl;

import lombok.extern.slf4j.Slf4j;
import test.cn.jinty.sql.code.entity.Job;
import test.cn.jinty.sql.code.mapper.ext.JobMapperExt;
import test.cn.jinty.sql.code.service.JobService;

import java.util.Date;
import java.util.List;

/**
 * 作业表 - ServiceImpl
 *
 * @author Jinty
 * @date 2024/09/21
 */
@Slf4j
public class JobServiceImpl implements JobService {

    private static final String EVENT = "作业表";

    private JobMapperExt mapper;

    @Override
    public boolean insert(Job item) {
        try {
            return mapper.insert(item) > 0;
        } catch (Exception e) {
            log.error(String.format("%s-保存失败：item=%s", EVENT, item), e);
            throw new RuntimeException(String.format("%s-保存失败", EVENT));
        }
    }

    @Override
    public boolean batchInsert(List<Job> list) {
        try {
            return mapper.batchInsert(list) > 0;
        } catch (Exception e) {
            log.error(String.format("%s-批量保存失败：list=%s", EVENT, list), e);
            throw new RuntimeException(String.format("%s-批量保存失败", EVENT));
        }
    }

    @Override
    public boolean updateById(Job item) {
        try {
            item.setUpdateTime(new Date());
            return mapper.updateById(item) > 0;
        } catch (Exception e) {
            log.error(String.format("%s-更新失败：item=%s", EVENT, item), e);
            throw new RuntimeException(String.format("%s-更新失败", EVENT));
        }
    }

    @Override
    public boolean updateByParam(Job item, Job param) {
        try {
            item.setUpdateTime(new Date());
            param.setIsDeleted(0);
            return mapper.updateByParam(item, param) > 0;
        } catch (Exception e) {
            log.error(String.format("%s-更新失败：item=%s, param=%s", EVENT, item, param), e);
            throw new RuntimeException(String.format("%s-更新失败", EVENT));
        }
    }

    @Override
    public int logicDeleteByIds(List<Long> ids, String updatedBy) {
        try {
            int total = ids.size();
            int success = mapper.logicDeleteByIds(ids, updatedBy);
            log.info("{}-批量逻辑删除：ids={}, updatedBy={}, total={}, success={}, fail={}",
                    EVENT, ids, updatedBy, total, success, total - success);
            return success;
        } catch (Exception e) {
            log.error(String.format("%s-批量逻辑删除失败：ids=%s, updatedBy=%s", EVENT, ids, updatedBy), e);
            throw new RuntimeException(String.format("%s-批量逻辑删除失败", EVENT));
        }
    }

    @Override
    public Job selectById(Long id) {
        try {
            return mapper.selectById(id);
        } catch (Exception e) {
            log.error(String.format("%s-查询失败：id=%s", EVENT, id), e);
            throw new RuntimeException(String.format("%s-查询失败", EVENT));
        }
    }

    @Override
    public List<Job> selectByIds(List<Long> ids) {
        try {
            return mapper.selectByIds(ids);
        } catch (Exception e) {
            log.error(String.format("%s-查询失败：ids=%s", EVENT, ids), e);
            throw new RuntimeException(String.format("%s-查询失败", EVENT));
        }
    }

    @Override
    public List<Job> selectByParam(Job param) {
        try {
            param.setIsDeleted(0);
            return mapper.selectByParam(param);
        } catch (Exception e) {
            log.error(String.format("%s-查询失败：param=%s", EVENT, param), e);
            throw new RuntimeException(String.format("%s-查询失败", EVENT));
        }
    }

    @Override
    public int selectCount(Job param) {
        try {
            param.setIsDeleted(0);
            return mapper.selectCount(param);
        } catch (Exception e) {
            log.error(String.format("%s-查询数量失败：param=%s", EVENT, param), e);
            throw new RuntimeException(String.format("%s-查询数量失败", EVENT));
        }
    }

    @Override
    public List<Job> selectByPage(Job param, Integer start, Integer length) {
        try {
            param.setIsDeleted(0);
            return mapper.selectByPage(param, start, length);
        } catch (Exception e) {
            log.error(String.format("%s-分页查询失败：param=%s, start=%s, length=%s", EVENT, param, start, length), e);
            throw new RuntimeException(String.format("%s-分页查询失败", EVENT));
        }
    }

}