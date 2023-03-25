package test.cn.jinty.sql.code;

import lombok.Data;

import java.util.Date;

/**
 * 作业表
 *
 * @author Jinty
 * @date 2023/03/25
 */
@Data
public class Job {

    // 自增ID
    private Long id;

    // 作业类型
    private String jobType;

    // 作业描述
    private String jobDesc;

    // 处理状态：INIT 初始，RUNNING 运行中，FINISHED 已结束，CANCELED 已取消
    private String processStatus;

    // 执行结果：PENDING 待定，SUCCESS 成功，FAIL 失败
    private String resultStatus;

    // 开始时间
    private Date startTime;

    // 结束时间
    private Date endTime;

    // 耗时(单位毫秒)
    private Long duration;

    // 是否删除：0 否，1 是
    private Integer isDeleted;

    // 创建者
    private String createdBy;

    // 创建时间
    private Date createTime;

    // 更新者
    private String updatedBy;

    // 更新时间
    private Date updateTime;

}
