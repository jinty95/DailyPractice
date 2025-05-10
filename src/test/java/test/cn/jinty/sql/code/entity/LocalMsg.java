package test.cn.jinty.sql.code.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 本地消息表
 *
 * @author Jinty
 * @date 2025/05/10
 */
@Data
@NoArgsConstructor
public class LocalMsg {

    // 自增ID
    private Long id;

    // 消息业务类型（不同类型有不同处理逻辑）
    private Integer msgType;

    // 消息状态：0 待消费，1 已消费，2 消费失败
    private Integer msgStatus;

    // 消息重试次数
    private Integer msgRetry;

    // 消息处理异常信息
    private String msgErrInfo;

    // 消息内容(JSON字符串)
    private String msgContent;

    // 首次消费时间
    private Date firstConsumeTime;

    // 末次消费时间
    private Date lastConsumeTime;

    // 备注
    private String remark;

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
