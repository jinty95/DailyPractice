package test.cn.jinty.sql.code;

import cn.jinty.sql.code.CodeGenerator;
import cn.jinty.sql.ddl.DDLParser;
import cn.jinty.sql.entity.Table;
import cn.jinty.sql.mapper.MysqlTypeMapper;
import cn.jinty.sql.mapper.TypeMapper;
import cn.jinty.sql.validate.TableValidation;
import cn.jinty.util.JdbcUtil;
import cn.jinty.util.io.FilePathUtil;
import cn.jinty.util.io.FileUtil;
import cn.jinty.util.string.StringUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

import static cn.jinty.sql.code.TemplatePlaceholderEnum.AUTHOR;
import static cn.jinty.sql.code.TemplatePlaceholderEnum.BASE_PACKAGE;

/**
 * 代码生成器 - 测试
 *
 * @author Jinty
 * @date 2023/3/6
 **/
public class CodeGeneratorTest {

    // 解析配置文件
    private static Properties props;

    static {
        try {
            props = FileUtil.parseProperties(new File(
                    FilePathUtil.getAbsolutePath("/properties/codegen.properties", true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 插入与删除不需要考虑逻辑删除。
    // 更新与查询默认针对非逻辑删除的数据，如果需要处理逻辑删除的数据，自行实现。
    
    @Test
    public void testGen() {
        gen(getDDL());
    }

    @Test
    public void testBatchGen() {
        long begin = System.currentTimeMillis();
        String targetTableNames = props.getProperty("targetTableNames");
        Set<String> targetTableNameSet = new HashSet<>();
        if (StringUtil.isNotBlank(targetTableNames) && !"*".equals(targetTableNames)) {
            targetTableNameSet = new HashSet<>(Arrays.asList(targetTableNames.split(",")));
        }
        int cnt = 0;
        try {
            for (String ddl : getDDLFromDB()) {
                Table table = DDLParser.parse(ddl);
                if (targetTableNameSet.isEmpty() || targetTableNameSet.contains(table.getName())) {
                    gen(ddl);
                    cnt++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.printf("代码生成结束，一共生成%s个表的对应代码，耗时%s毫秒%n", cnt, (end - begin));
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
        String driver = props.getProperty("db.driver");
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        try (Connection conn = JdbcUtil.getConnection(driver, url, user, password)) {
            return JdbcUtil.getAllCreateTable(conn);
        }
    }

    // 生成文件
    private void gen(String ddl) {
        try {
            // 指定类型映射
            TypeMapper typeMapper = new MysqlTypeMapper();
            // 指定包名及作者
            Map<String, String> data = new HashMap<>();
            data.put(BASE_PACKAGE.name(), props.getProperty("basePackage"));
            data.put(AUTHOR.name(), props.getProperty("author"));
            // 指定校验数据
            TableValidation validation = TableValidation.parseFromProps(props);
            // 指定生成哪些文件
            for (String type : props.getProperty("genType").split(",")) {
                // 指定模板路径
                String templateFilePath = FilePathUtil.getAbsolutePath(
                        props.getProperty("relativeTemplateFilePath." + type), true);
                // 指定目标目录
                String targetDir = FilePathUtil.concatBySeparator(props.getProperty("baseTargetDir"),
                        props.getProperty("basePackage").replace(".", "/"),
                        props.getProperty("targetDir." + type)
                );
                // 指定文件后缀
                String targetFileSuffix = props.getProperty("targetFileSuffix." + type);
                // 生成文件
                CodeGenerator.generate(ddl, typeMapper, validation, data, templateFilePath, targetDir, targetFileSuffix);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
