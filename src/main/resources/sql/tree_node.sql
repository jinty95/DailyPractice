# 树型节点表
DROP TABLE IF EXISTS `tree_node`;
CREATE TABLE `tree_node` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `node_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '节点名称',
    `node_level` INT NOT NULL DEFAULT 1 COMMENT '节点层级(从第1层开始)',
    `parent_id` INT NOT NULL DEFAULT 0 COMMENT '父级ID',
    `parent_path` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '父级路径(从第1层起，节点ID路径，使用逗号分隔)',
    `order_num` INT NOT NULL DEFAULT 0 COMMENT '序号(对同层级而言)',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',
    `created_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB COMMENT='树型节点表';

INSERT INTO `tree_node`(`node_name`, `node_level`, `parent_id`, `parent_path`) VALUES('中国', 1, 0, '');
INSERT INTO `tree_node`(`node_name`, `node_level`, `parent_id`, `parent_path`) VALUES('广东省', 2, 1, '1');
INSERT INTO `tree_node`(`node_name`, `node_level`, `parent_id`, `parent_path`) VALUES('福建省', 2, 1, '1');
INSERT INTO `tree_node`(`node_name`, `node_level`, `parent_id`, `parent_path`) VALUES('广州市', 3, 2, '1,2');
INSERT INTO `tree_node`(`node_name`, `node_level`, `parent_id`, `parent_path`) VALUES('深圳市', 3, 2, '1,2');
INSERT INTO `tree_node`(`node_name`, `node_level`, `parent_id`, `parent_path`) VALUES('厦门市', 3, 3, '1,3');
INSERT INTO `tree_node`(`node_name`, `node_level`, `parent_id`, `parent_path`) VALUES('福州市', 3, 3, '1,3');
