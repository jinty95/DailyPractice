# 工作流表
DROP TABLE IF EXISTS `workflow`;
CREATE TABLE `workflow` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `type` TINYINT NOT NULL DEFAULT 1 COMMENT '工作流类型：1 请假申请，2 资源申请',
  `name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '工作流名称',
  `apply_template_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '申请模板ID',
  `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',
  `created_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='工作流表';

# 工作流节点表
DROP TABLE IF EXISTS `workflow_node`;
CREATE TABLE `workflow_node` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `type` TINYINT NOT NULL DEFAULT 1 COMMENT '节点类型：1 审批，2 执行',
  `name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '节点名称',
  `order_num` INT NOT NULL DEFAULT 0 COMMENT '节点序号',
  `last_node_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '上一节点ID',
  `rel_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '关联ID(用户ID/动作ID)',
  `workflow_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '工作流ID',
  `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',
  `created_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_workflow_id` (`workflow_id`) USING BTREE
) ENGINE=InnoDB COMMENT='工作流节点表';

# 工作流申请模板
DROP TABLE IF EXISTS `workflow_apply_template`;
CREATE TABLE `workflow_apply_template` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `type` TINYINT NOT NULL DEFAULT 1 COMMENT '模板类型：1 请假申请，2 资源申请',
  `name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '模板名称',
  `html` TEXT NOT NULL COMMENT '模板页面',
  `model` TEXT NOT NULL COMMENT '模板对象模型',
  `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',
  `created_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='工作流申请模板';

# 工作流申请表
DROP TABLE IF EXISTS `workflow_apply`;
CREATE TABLE `workflow_apply` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `workflow_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '工作流ID',
  `params` TEXT NOT NULL COMMENT '输入参数',
  `apply_status` TINYINT NOT NULL DEFAULT 1 COMMENT '申请状态：1 新建，2 审批中，3 通过，4 驳回，5 撤销',
  `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',
  `created_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='工作流申请表';

# 工作流日志表
DROP TABLE IF EXISTS `workflow_log`;
CREATE TABLE `workflow_log` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `apply_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '申请ID',
  `cur_node_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '当前节点ID',
  `next_node_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '下一节点ID',
  `node_status` TINYINT NOT NULL DEFAULT 1 COMMENT '节点状态：1 进行中，2 成功，3 失败',
  `remark` TEXT NULL COMMENT '备注',
  `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',
  `created_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_apply_id` (`apply_id`) USING BTREE
) ENGINE=InnoDB COMMENT='工作流日志表';

INSERT `workflow` (`type`, `name`, `apply_template_id`) VALUES (1, '内部人员请假申请', 1);

INSERT `workflow_node` (`type`, `name`, `order_num`, `last_node_id`, `rel_id`, `workflow_id`) VALUES (1, '小组长审批', 1, 0, 1, 1);
INSERT `workflow_node` (`type`, `name`, `order_num`, `last_node_id`, `rel_id`, `workflow_id`) VALUES (1, '直属上级审批', 2, 1, 2, 1);
INSERT `workflow_node` (`type`, `name`, `order_num`, `last_node_id`, `rel_id`, `workflow_id`) VALUES (1, '总监审批', 3, 2, 3, 1);

INSERT `workflow_apply_template` (`type`, `name`, `html`, `model`) VALUES (1, '请假申请通用模板', '<form>请假理由：<input type="text"/><br/><input type="submit"/></form>', '{"reason": ""}');

INSERT `workflow_apply` (`workflow_id`, `params`, `apply_status`) VALUES (1, '{"reason": "肚子不舒服"}', 2);

INSERT `workflow_log` (`apply_id`, `cur_node_id`, `next_node_id`, `node_status`, `remark`) VALUES (1, 1, 2, 2, '同意，多喝热水，好好休息');
INSERT `workflow_log` (`apply_id`, `cur_node_id`, `next_node_id`, `node_status`, `remark`) VALUES (1, 2, 3, 2, '同意，多喝热水，好好休息');
INSERT `workflow_log` (`apply_id`, `cur_node_id`, `next_node_id`, `node_status`, `remark`) VALUES (1, 3, 0, 1, null);
