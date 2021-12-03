# 评论表(两层结构，评论下所有评论都挂在当前评论，按照时间升序，通过回复用户确定评论指向)
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `target_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '目标ID',
    `target_type` TINYINT NOT NULL DEFAULT 0 COMMENT '目标类型：1 问题，2 回答，3 评论',
    `from_user_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '发起用户ID',
    `to_user_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '回复用户ID',
    `content` TEXT NOT NULL COMMENT '评论内容',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_target_id_target_type` (`target_id`, `target_type`) USING BTREE
) ENGINE=InnoDB COMMENT='评论表';

INSERT INTO `comment`(`target_id`, `target_type`, `from_user_id`, `to_user_id`, `content`) VALUES (1, 1, 1, 0, '这个问题很有意思！');
INSERT INTO `comment`(`target_id`, `target_type`, `from_user_id`, `to_user_id`, `content`) VALUES (1, 2, 1, 0, '这个回答也很不错！');
INSERT INTO `comment`(`target_id`, `target_type`, `from_user_id`, `to_user_id`, `content`) VALUES (2, 3, 2, 1, '你说哪里很不错呢？');
INSERT INTO `comment`(`target_id`, `target_type`, `from_user_id`, `to_user_id`, `content`) VALUES (2, 3, 3, 2, '他说的应该是这个吧');
INSERT INTO `comment`(`target_id`, `target_type`, `from_user_id`, `to_user_id`, `content`) VALUES (2, 3, 1, 3, '嗯嗯，你说的很对！');
