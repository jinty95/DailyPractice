# 作业表
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `job_type` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '作业类型',
    `job_desc` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '作业描述',
    `process_status` VARCHAR(10) NOT NULL DEFAULT 'INIT' COMMENT '处理状态：INIT 初始，RUNNING 运行中，FINISHED 已结束，CANCELED 已取消',
    `result_status` VARCHAR(10) NOT NULL DEFAULT 'PENDING' COMMENT '执行结果：PENDING 待定，SUCCESS 成功，FAIL 失败',
    `start_time` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开始时间',
    `end_time` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
    `duration` BIGINT NOT NULL DEFAULT 0 COMMENT '耗时(单位毫秒)',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',
    `creator` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='作业表';

DROP TABLE IF EXISTS `job_param`;
CREATE TABLE `job_param` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `job_id` BIGINT NOT NULL DEFAULT 0 COMMENT '作业ID',
    `content` TEXT NOT NULL COMMENT '参数内容',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_job_id` (`job_id`) USING BTREE
) ENGINE=InnoDB COMMENT='作业参数表';

DROP TABLE IF EXISTS `job_result`;
CREATE TABLE `job_result` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `job_id` BIGINT NOT NULL DEFAULT 0 COMMENT '作业ID',
    `content` TEXT NOT NULL COMMENT '结果内容',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_job_id` (`job_id`) USING BTREE
) ENGINE=InnoDB COMMENT='作业结果表';

INSERT INTO `job` (`job_type`, `job_desc`) VALUES ('UPDATE_GOODS', '刷新商品信息');
INSERT INTO `job_param` (`job_id`, `content`) VALUES (1, '{"goodsId": 1}');
UPDATE `job` SET `process_status` = 'FINISHED', `result_status` = 'SUCCESS', `start_time` = '2021-12-01 13:05:00', `end_time` = '2021-12-01 13:05:01', `duration` = 1000 WHERE id = 1;
INSERT INTO `job_result` (`job_id`, `content`) VALUES (1, '成功更新1行记录');
