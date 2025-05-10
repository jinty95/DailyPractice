# 本地消息表
DROP TABLE IF EXISTS `local_msg`;
CREATE TABLE `local_msg` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `msg_type` INT NOT NULL DEFAULT 0 COMMENT '消息业务类型（不同类型有不同处理逻辑）',
  `msg_status` TINYINT NOT NULL DEFAULT 0 COMMENT '消息状态：0 待消费，1 已消费，2 消费失败',
  `msg_retry` INT NOT NULL DEFAULT 0 COMMENT '消息重试次数',
  `msg_err_info` VARCHAR(500) NOT NULL DEFAULT '' COMMENT '消息处理异常信息',
  `msg_content` TEXT NOT NULL COMMENT '消息内容(JSON字符串)',
  `first_consume_time` TIMESTAMP NULL COMMENT '首次消费时间',
  `last_consume_time` TIMESTAMP NULL COMMENT '末次消费时间',
  `remark` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '备注',
  `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',
  `created_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_msg_type_msg_status_msg_retry` (`msg_type`, `msg_status`,`msg_retry`) USING BTREE,
  INDEX `idx_create_time` (`create_time`) USING BTREE,
  INDEX `idx_update_time` (`update_time`) USING BTREE
) ENGINE=InnoDB COMMENT='本地消息表';

# 作用：将第三方的消息先保存到本地，再启用定时任务去消费
# 直接程序中开启异步线程，存在异步线程失败从而导致业务丢失的问题，通过这种方式持久化，可以跟踪执行情况，并且支持重试，健壮性更好

# 正常消息首次消费：
# 取状态为0，按创建时间(或id)升序(保证先进先出)的N条消息，逐条处理，状态转为1或2，定时任务每次只处理N条消息。
# select * from local_msg where msg_type = 1 and msg_status = 0 order by create_time asc limit 1

# 失败消息重新消费：
# 取状态为2，按重试次数+创建时间(或id)升序(保证均匀重试、先进先出)的N条消息，逐条处理，重试次数+1，状态转为1或2，定时任务每次只处理N条消息。
# select * from local_msg where msg_type = 1 and msg_status = 2 and msg_retry < 5 order by msg_retry asc, create_time asc limit 1

# 表的数据特征：
# 正常情况下，大部分数据的消息状态都是1，0和2都是少数，所以对消息状态建立索引是有意义的。

# 通过归档减少数据量：
# 对于历史数据，一般不需要保留太久，可以只保留指定时间段的数据（例如最近3个月），其余数据归档处理，
# 可以保证这个表的数据量维持在一个稳定的量级，不至于随着时间推移而不断膨胀，最终出现查询性能问题，影响实时处理。
