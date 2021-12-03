# 文章表
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `title` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '标题',
    `content` TEXT NOT NULL COMMENT '内容',
    `version_num` INT NOT NULL DEFAULT 1 COMMENT '版本号',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',
    `created_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='文章表';

# 文章历史表 (文章每次变更都对可变字段做一次快照)
DROP TABLE IF EXISTS `article_history`;
CREATE TABLE `article_history` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `article_id` INT NOT NULL DEFAULT 0 COMMENT '文章ID',
    `title` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '标题',
    `content` TEXT NOT NULL COMMENT '内容',
    `version_num` INT NOT NULL DEFAULT 1 COMMENT '版本号',
    `updated_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_article_id` (`article_id`) USING BTREE
) ENGINE=InnoDB COMMENT='文章历史表';

INSERT INTO `article` (`title`, `content`, `created_by`, `updated_by`) VALUES ('标题1', '第1版的内容', 'me', 'me');

INSERT INTO `article_history` (`article_id`, `title`, `content`, `version_num`, `updated_by`, `update_time`)
SELECT `id` AS `article_id`, `title`, `content`, `version_num`, `updated_by`, `update_time` FROM `article` WHERE `id` = 1;
UPDATE `article` SET `title` = '标题2啊', `content` = '第2版的内容哈哈', `version_num` = `version_num` + 1 WHERE `id` = 1;

INSERT INTO `article_history` (`article_id`, `title`, `content`, `version_num`, `updated_by`, `update_time`)
SELECT `id` AS `article_id`, `title`, `content`, `version_num`, `updated_by`, `update_time` FROM `article` WHERE `id` = 1;
UPDATE `article` SET `title` = '标题3额', `content` = '第3版的内容嘻嘻', `version_num` = `version_num` + 1 WHERE `id` = 1;
