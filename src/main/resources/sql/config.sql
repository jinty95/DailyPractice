# 配置表
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `config_type` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '配置类型',
    `config_key` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '配置键',
    `config_value` VARCHAR(1000) NOT NULL DEFAULT '' COMMENT '配置值',
    `description` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '说明',
    `remark` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '备注',
    `is_enabled` TINYINT NOT NULL DEFAULT 1 COMMENT '是否有效：0 否，1 是',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',
    `created_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE `uniq_config_type_config_key` (`config_type`, `config_key`) USING BTREE,
    INDEX `idx_update_time` (`update_time`) USING BTREE
) ENGINE=InnoDB COMMENT='配置表';

INSERT INTO `config`(`config_type`, `config_key`, `config_value`, `description`) VALUES('WX_INF_PARAM', 'APP_ID', 'xxx', '微信接口参数-应用ID');
INSERT INTO `config`(`config_type`, `config_key`, `config_value`, `description`) VALUES('WX_INF_PARAM', 'SECRET', 'xxx', '微信接口参数-秘钥');
INSERT INTO `config`(`config_type`, `config_key`, `config_value`, `description`) VALUES('WX_INF_PARAM', 'TOKEN', 'xxx', '微信接口参数-凭证');

