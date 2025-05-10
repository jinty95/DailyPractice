package test.cn.jinty.sql.code;

import cn.jinty.sql.code.CodeGenerator;
import org.junit.Test;

import java.io.IOException;

/**
 * 代码生成器 - 测试
 *
 * @author Jinty
 * @date 2023/3/6
 **/
public class CodeGeneratorTest {

    // 插入与删除不需要考虑逻辑删除。
    // 更新与查询默认针对非逻辑删除的数据，如果需要处理逻辑删除的数据，自行实现。

    @Test
    public void testGen() {
        try {
            CodeGenerator.generate(getDDL());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBatchGen() {
        try {
            CodeGenerator.batchGenerate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* 以下为内部函数 */

    // 直接写DDL
    // 注意：表名和字段名的`不能省略，否则解析会出错
    private String getDDL() {
        return "# 本地消息表\n" +
                "DROP TABLE IF EXISTS `local_msg`;\n" +
                "CREATE TABLE `local_msg` (\n" +
                "  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',\n" +
                "  `msg_type` INT NOT NULL DEFAULT 0 COMMENT '消息业务类型（不同类型有不同处理逻辑）',\n" +
                "  `msg_status` TINYINT NOT NULL DEFAULT 0 COMMENT '消息状态：0 待消费，1 已消费，2 消费失败',\n" +
                "  `msg_retry` INT NOT NULL DEFAULT 0 COMMENT '消息重试次数',\n" +
                "  `msg_err_info` VARCHAR(500) NOT NULL DEFAULT '' COMMENT '消息处理异常信息',\n" +
                "  `msg_content` TEXT NOT NULL COMMENT '消息内容(JSON字符串)',\n" +
                "  `first_consume_time` TIMESTAMP NULL COMMENT '首次消费时间',\n" +
                "  `last_consume_time` TIMESTAMP NULL COMMENT '末次消费时间',\n" +
                "  `remark` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '备注',\n" +
                "  `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',\n" +
                "  `created_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',\n" +
                "  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                "  `updated_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',\n" +
                "  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',\n" +
                "  PRIMARY KEY (`id`) USING BTREE,\n" +
                "  INDEX `idx_msg_type_msg_status_msg_retry` (`msg_type`, `msg_status`,`msg_retry`) USING BTREE,\n" +
                "  INDEX `idx_create_time` (`create_time`) USING BTREE,\n" +
                "  INDEX `idx_update_time` (`update_time`) USING BTREE\n" +
                ") ENGINE=InnoDB COMMENT='本地消息表';";
    }

}
