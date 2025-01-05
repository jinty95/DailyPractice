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
        return "# 作业表\n" +
                "DROP TABLE IF EXISTS `job`;\n" +
                "CREATE TABLE `job` (\n" +
                "    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增ID',\n" +
                "    `job_type` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '作业类型',\n" +
                "    `job_desc` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '作业描述',\n" +
                "    `process_status` VARCHAR(10) NOT NULL DEFAULT 'INIT' COMMENT '处理状态：INIT 初始，RUNNING 运行中，FINISHED 已结束，CANCELED 已取消',\n" +
                "    `result_status` VARCHAR(10) NOT NULL DEFAULT 'PENDING' COMMENT '执行结果：PENDING 待定，SUCCESS 成功，FAIL 失败',\n" +
                "    `start_time` DATETIME NOT NULL DEFAULT '0001-01-01 00:00:00' COMMENT '开始时间',\n" +
                "    `end_time` DATETIME NOT NULL DEFAULT '0001-01-01 00:00:00' COMMENT '结束时间',\n" +
                "    `duration` BIGINT NOT NULL DEFAULT 0 COMMENT '耗时(单位毫秒)',\n" +
                "    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',\n" +
                "    `created_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',\n" +
                "    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                "    `updated_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',\n" +
                "    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',\n" +
                "    PRIMARY KEY (`id`) USING BTREE\n" +
                ") ENGINE=InnoDB COMMENT='作业表';";
    }

}
