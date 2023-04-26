package test.cn.jinty.sql.code;

import cn.jinty.Main;
import cn.jinty.sql.code.CodeGenerator;
import cn.jinty.sql.mapper.MysqlTypeMapper;
import cn.jinty.sql.mapper.TypeMapper;
import cn.jinty.util.JdbcUtil;
import cn.jinty.util.io.FilePathUtil;
import cn.jinty.util.io.FileUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.jinty.sql.code.TemplatePlaceholderEnum.AUTHOR;
import static cn.jinty.sql.code.TemplatePlaceholderEnum.BASE_PACKAGE;

/**
 * 代码生成器 - 测试
 *
 * @author Jinty
 * @date 2023/3/6
 **/
public class CodeGeneratorTest {

    @Test
    public void testGen() {
        gen(getDDL());
    }

    @Test
    public void testBatchGen() {
        try {
            for (String ddl : getDDLFromDB()) {
                gen(ddl);
            }
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
        String ddlFilePath = FilePathUtil.getAbsolutePath(filePath, isRelative);
        System.out.println("DDL文件路径：" + ddlFilePath);
        return FileUtil.read(new File(ddlFilePath));
    }

    // 从目录读取多个DDL
    private List<String> getDDLFromDir(String dirPath, boolean isRelative) throws IOException {
        String ddlDirPath = FilePathUtil.getAbsolutePath(dirPath, isRelative);
        System.out.println("DDL目录路径：" + ddlDirPath);
        List<File> ddlFiles = FileUtil.scanFilesOfRoot(new File(ddlDirPath));
        List<String> ddlList = new ArrayList<>();
        for (File ddlFile : ddlFiles) {
            String ddlFilePath = ddlFile.getAbsolutePath();
            System.out.println("DDL文件路径：" + ddlFilePath);
            ddlList.add(FileUtil.read(new File(ddlFilePath)));
        }
        return ddlList;
    }

    // 从数据库获取所有DDL
    private List<String> getDDLFromDB() throws Exception {
        try (Connection conn = JdbcUtil.getDefaultConnection()) {
            return JdbcUtil.getAllCreateTable(conn);
        }
    }

    // 获取生成文件目录
    private String getTargetDir(String lastPackage) {
        String dir = FilePathUtil.getAbsolutePath("sql", true, Main.class);
        String basePackage = getBasePackage().replace(".", File.separator);
        return FilePathUtil.concatBySeparator(dir, basePackage, lastPackage);
    }

    // 获取包名
    private String getBasePackage() {
        return "codegen";
    }

    // 获取作者
    private String getAuthor() {
        return "Jinty";
    }

    // 获取包名及作者
    private Map<String, String> getData() {
        Map<String, String> data = new HashMap<>();
        data.put(BASE_PACKAGE.name(), getBasePackage());
        data.put(AUTHOR.name(), getAuthor());
        return data;
    }

    // 生成文件
    private void gen(String ddl, String relativeTemplateFilePath, String targetDir, String targetFileSuffix) {
        try {
            // 指定模板路径
            String templateFilePath = FilePathUtil.getAbsolutePath(relativeTemplateFilePath, true);
            // 指定类型映射
            TypeMapper typeMapper = new MysqlTypeMapper();
            // 指定包名及作者
            Map<String, String> data = getData();
            // 生成文件
            CodeGenerator.generate(ddl, typeMapper, data, templateFilePath, targetDir, targetFileSuffix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 生成文件
    private void gen(String ddl) {
        gen(ddl, "/template/java_entity_template.txt", getTargetDir("entity"), ".java");
        gen(ddl, "/template/mybatis_xml_template.txt", getTargetDir("mapper"), "Mapper.xml");
        gen(ddl, "/template/mybatis_mapper_template.txt", getTargetDir("mapper"), "Mapper.java");
    }

}
