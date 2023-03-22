package test.cn.jinty.sql.code;

import cn.jinty.sql.code.CodeGenerator;
import cn.jinty.sql.mapper.MysqlTypeMapper;
import cn.jinty.sql.mapper.TypeMapper;
import cn.jinty.util.JdbcUtil;
import cn.jinty.util.io.FileUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.jinty.sql.code.JavaEntityTemplatePlaceholderEnum.*;
import static cn.jinty.sql.code.MybatisXmlTemplatePlaceholderEnum.*;

/**
 * 代码生成器 - 测试
 *
 * @author Jinty
 * @date 2023/3/6
 **/
public class CodeGeneratorTest {

    @Test
    public void testGenJavaEntity() {
        try {
            // 获取DDL
            String ddl = getDDLFromFile("/sql/base_table.sql", true);
            // 指定模板路径
            String templateFilePath = FileUtil.getAbsolutePath("/template/java_entity_template.txt", true);
            // 指定输出目录
            String targetDir = "D:/temp/codegen";
            // 生成Java文件
            TypeMapper typeMapper = new MysqlTypeMapper();
            Map<String, String> valueMapper = new HashMap<>();
            valueMapper.put(PACKAGE_NAME.name(), "cn.jinty.entity");
            valueMapper.put(AUTHOR.name(), "Jinty");
            CodeGenerator.genJavaEntity(ddl, typeMapper, valueMapper, templateFilePath, targetDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBatchGenJavaEntity() {
        try {
            // 获取DDL
            List<String> ddlList = getDDLFromDir("/sql", true);
            // 指定模板路径
            String templateFilePath = FileUtil.getAbsolutePath("/template/java_entity_template.txt", true);
            // 指定输出目录
            String targetDir = "D:/temp/codegen";
            // 生成Java文件
            TypeMapper typeMapper = new MysqlTypeMapper();
            for (String ddl : ddlList) {
                Map<String, String> valueMapper = new HashMap<>();
                valueMapper.put(PACKAGE_NAME.name(), "cn.jinty.entity");
                valueMapper.put(AUTHOR.name(), "Jinty");
                CodeGenerator.genJavaEntity(ddl, typeMapper, valueMapper, templateFilePath, targetDir);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBatchGenJavaEntity1() {
        try {
            // 获取DDL
            List<String> ddlList = getDDLFromDB();
            // 指定模板路径
            String templateFilePath = FileUtil.getAbsolutePath("/template/java_entity_template.txt", true);
            // 指定输出目录
            String targetDir = "D:/temp/codegen";
            // 生成Java文件
            TypeMapper typeMapper = new MysqlTypeMapper();
            for (String ddl : ddlList) {
                Map<String, String> valueMapper = new HashMap<>();
                valueMapper.put(PACKAGE_NAME.name(), "cn.jinty.entity");
                valueMapper.put(AUTHOR.name(), "Jinty");
                CodeGenerator.genJavaEntity(ddl, typeMapper, valueMapper, templateFilePath, targetDir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGenMybatisXml() {
        try {
            // 获取DDL
            String ddl = getDDL();
            // 指定模板路径
            String templateFilePath = FileUtil.getAbsolutePath("/template/mybatis_xml_template.txt", true);
            // 指定输出目录
            String targetDir = "D:/temp/codegen";
            // 生成Mybatis的XML文件
            TypeMapper typeMapper = new MysqlTypeMapper();
            Map<String, String> valueMapper = new HashMap<>();
            valueMapper.put(ENTITY_PACKAGE_NAME.name(), "cn.jinty.entity");
            valueMapper.put(MAPPER_PACKAGE_NAME.name(), "cn.jinty.mapper");
            CodeGenerator.genMybatisXml(ddl, typeMapper, valueMapper, templateFilePath, targetDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* 以下为内部函数 */

    // 直接写DDL
    private String getDDL() {
        return "# 作业表\n" +
                "DROP TABLE IF EXISTS `job`;\n" +
                "CREATE TABLE `job` (\n" +
                "    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增ID',\n" +
                "    `job_type` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '作业类型',\n" +
                "    `job_desc` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '作业描述',\n" +
                "    `process_status` VARCHAR(10) NOT NULL DEFAULT 'INIT' COMMENT '处理状态：INIT 初始，RUNNING 运行中，FINISHED 已结束，CANCELED 已取消',\n" +
                "    `result_status` VARCHAR(10) NOT NULL DEFAULT 'PENDING' COMMENT '执行结果：PENDING 待定，SUCCESS 成功，FAIL 失败',\n" +
                "    `start_time` DATETIME NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '开始时间',\n" +
                "    `end_time` DATETIME NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '结束时间',\n" +
                "    `duration` BIGINT NOT NULL DEFAULT 0 COMMENT '耗时(单位毫秒)',\n" +
                "    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',\n" +
                "    `created_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',\n" +
                "    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                "    `updated_by` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',\n" +
                "    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',\n" +
                "    PRIMARY KEY (`id`) USING BTREE\n" +
                ") ENGINE=InnoDB COMMENT='作业表';";
    }

    // 从文件读取DDL
    private String getDDLFromFile(String filePath, boolean isRelative) throws IOException {
        String ddlFilePath = FileUtil.getAbsolutePath(filePath, isRelative);
        System.out.println("DDL文件路径：" + ddlFilePath);
        return FileUtil.read(ddlFilePath);
    }

    // 从目录读取多个DDL
    private List<String> getDDLFromDir(String dirPath, boolean isRelative) throws IOException {
        String ddlDirPath = FileUtil.getAbsolutePath(dirPath, isRelative);
        System.out.println("DDL目录路径：" + ddlDirPath);
        List<File> ddlFiles = FileUtil.scanFilesOfRoot(new File(ddlDirPath));
        List<String> ddlList = new ArrayList<>();
        for (File ddlFile : ddlFiles) {
            String ddlFilePath = ddlFile.getAbsolutePath();
            System.out.println("DDL文件路径：" + ddlFilePath);
            ddlList.add(FileUtil.read(ddlFilePath));
        }
        return ddlList;
    }

    // 从数据库获取所有DDL
    private List<String> getDDLFromDB() throws Exception {
        List<String> ddlList = new ArrayList<>();
        try (Connection conn = JdbcUtil.getDefaultConnection()) {
            String sql = "show tables";
            List<Map<String, String>> tables = JdbcUtil.executeQuery(conn, sql);
            for (Map<String, String> table : tables) {
                sql = String.format("show create table `%s`", table.entrySet().iterator().next().getValue());
                List<Map<String, String>> createTable = JdbcUtil.executeQuery(conn, sql);
                ddlList.add(createTable.get(0).get("Create Table"));
            }
        }
        return ddlList;
    }

}
