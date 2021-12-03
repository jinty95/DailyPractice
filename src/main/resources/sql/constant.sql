# 枚举常量表
DROP TABLE IF EXISTS `constant`;
CREATE TABLE `constant` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `code` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '编码',
    `meaning` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '含义',
    `parent_code` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '上级编码',
    `order_num` INT NOT NULL DEFAULT 0 COMMENT '序号(对同层级而言)',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE `uniq_parent_code_code` (`parent_code`, `code`) USING BTREE
) ENGINE=InnoDB COMMENT='枚举常量表';

INSERT INTO `constant`(`code`, `meaning`, `parent_code`) VALUES('sex', '性别', '');
INSERT INTO `constant`(`code`, `meaning`, `parent_code`) VALUES('male', '男', 'sex');
INSERT INTO `constant`(`code`, `meaning`, `parent_code`) VALUES('female', '女', 'sex');
INSERT INTO `constant`(`code`, `meaning`, `parent_code`) VALUES('unknown', '未知', 'sex');
INSERT INTO `constant`(`code`, `meaning`, `parent_code`) VALUES('size', '尺码', '');
INSERT INTO `constant`(`code`, `meaning`, `parent_code`) VALUES('XL', '加大', 'size');
INSERT INTO `constant`(`code`, `meaning`, `parent_code`) VALUES('L', '大', 'size');
INSERT INTO `constant`(`code`, `meaning`, `parent_code`) VALUES('M', '中', 'size');
INSERT INTO `constant`(`code`, `meaning`, `parent_code`) VALUES('S', '小', 'size');
INSERT INTO `constant`(`code`, `meaning`, `parent_code`) VALUES('XS', '加小', 'size');
