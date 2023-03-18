# 附件表
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `source_table` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '来源表',
  `source_id` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '来源ID',
  `attachment_type` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '附件类型',
  `sort_num` INT NOT NULL DEFAULT 0 COMMENT '序号',
  `file_path` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '文件路径',
  `file_name` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '文件名',
  `file_type` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '文件类型',
  `file_size` BIGINT NOT NULL DEFAULT 0 COMMENT '文件大小',
  `remark` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '备注',
  `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',
  `created_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_source_table_source_id` (`source_table`, `source_id`) USING BTREE
) ENGINE=InnoDB COMMENT='附件表';

# 通过`source_table`和`source_id`两个字段，本表可以同时作为多个表的子表，并且子表主动去关联父表。
# 如果父表存在版本控制，则本表也需要做版本控制，这种情况尽量不使用这种方式，而是让父表主动关联子表。
