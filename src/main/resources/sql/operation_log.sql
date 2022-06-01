# 操作日志表(用于记录insert、update、delete等操作)
DROP TABLE if EXISTS `operation_log`;
CREATE TABLE `operation_log` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `user_id` BIGINT NOT NULL DEFAULT 0 COMMENT '用户ID',
    `user_name` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '用户名',
    `operation_type` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '操作类型：INSERT/UPDATE/DELETE',
    `table_name` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '表名',
    `table_comment` VARCHAR(128) DEFAULT NULL COMMENT '表注释',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='操作日志表';

DROP TABLE if EXISTS `operation_log_detail`;
CREATE TABLE `operation_log_detail` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `operation_log_id` BIGINT NOT NULL DEFAULT 0 COMMENT '操作日志ID',
    `column_name` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '字段名',
    `column_comment` VARCHAR(128) DEFAULT NULL COMMENT '字段注释',
    `old_value` VARCHAR(255) DEFAULT NULL COMMENT '字段旧值',
    `new_value` VARCHAR(255) DEFAULT NULL COMMENT '字段新值',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='操作日志明细表';

INSERT INTO `operation_log`(`user_id`, `user_name`, `operation_type`, `table_name`, `table_comment`) VALUES('1', 'admin', 'INSERT', 'tbl', '某表');
INSERT INTO `operation_log_detail`(`operation_log_id`, `column_name`, `column_comment`, `old_value`, `new_value`) VALUES(1, 'id', '自增ID', NULL, 1);
INSERT INTO `operation_log_detail`(`operation_log_id`, `column_name`, `column_comment`, `old_value`, `new_value`) VALUES(1, 'col', '某字段', NULL, 'xxx');
INSERT INTO `operation_log`(`user_id`, `user_name`, `operation_type`, `table_name`, `table_comment`) VALUES('1', 'admin', 'UPDATE', 'tbl', '某表');
INSERT INTO `operation_log_detail`(`operation_log_id`, `column_name`, `column_comment`, `old_value`, `new_value`) VALUES(2, 'col', '某字段', 'xxx', 'yyy');
INSERT INTO `operation_log`(`user_id`, `user_name`, `operation_type`, `table_name`, `table_comment`) VALUES('1', 'admin', 'DELETE', 'tbl', '某表');
INSERT INTO `operation_log_detail`(`operation_log_id`, `column_name`, `column_comment`, `old_value`, `new_value`) VALUES(3, 'id', '自增ID', 1, NULL);
INSERT INTO `operation_log_detail`(`operation_log_id`, `column_name`, `column_comment`, `old_value`, `new_value`) VALUES(3, 'col', '某字段', 'yyy', NULL);
