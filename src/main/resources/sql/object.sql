# 对象表
DROP TABLE IF EXISTS `object`;
CREATE TABLE `object` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `object_type` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '对象类型',
  `field_name` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '字段名',
  `field_value` VARCHAR(1000) NOT NULL DEFAULT '' COMMENT '字段值',
  `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',
  `created_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='对象表';