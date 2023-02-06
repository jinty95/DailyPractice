# 本地消息表
DROP TABLE IF EXISTS `local_msg`;
CREATE TABLE `local_msg` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `msg_status` TINYINT NOT NULL DEFAULT 0 COMMENT '消息状态：0 待消费，1 已消费，2 消费失败',
  `msg_content` TEXT NOT NULL COMMENT '消息内容(JSON字符串)',
  `first_consume_time` TIMESTAMP NULL COMMENT '首次消费时间',
  `last_consume_time` TIMESTAMP NULL COMMENT '末次消费时间',
  `retry` INT NOT NULL DEFAULT 0 COMMENT '重试次数',
  `remark` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '备注',
  `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',
  `creator` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_msg_status` (`msg_status`) USING BTREE,
  INDEX `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB COMMENT='本地消息表';

# 作用：将第三方的消息先保存到本地，再启用定时任务去消费

# 正常消息消费：按创建时间升序(保证先进先出)，取状态为0的N条消息，逐条处理，状态转为1或2，定时任务每次只处理N条消息。

# 失败消息重新消费：按创建时间升序，重试次数升序，取状态为2的N条消息，逐条处理，重试次数+1，状态转为1或2，定时任务每次只处理N条消息。
