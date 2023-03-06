package test.cn.jinty.sql.ddl;

import cn.jinty.sql.ddl.DDLParser;
import cn.jinty.sql.ddl.DDLWord;
import cn.jinty.sql.entity.Table;
import org.junit.Test;

import java.util.List;

/**
 * DDL解析器 - 测试
 *
 * @author Jinty
 * @date 2023/3/5
 */
public class DDLParserTest {

    private String getDDL() {
        return "# 附件表\n" +
                "DROP TABLE IF EXISTS `attachment`;\n" +
                "CREATE TABLE `attachment` (\n" +
                "  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',\n" +
                "  `source_table` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '来源表',\n" +
                "  `source_id` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '来源ID',\n" +
                "  `attachment_type` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '附件类型',\n" +
                "  `sort_num` INT NOT NULL DEFAULT 0 COMMENT '序号',\n" +
                "  `file_path` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '文件路径',\n" +
                "  `file_name` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '文件名',\n" +
                "  `file_type` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '文件类型',\n" +
                "  `file_size` BIGINT NOT NULL DEFAULT 0 COMMENT '文件大小',\n" +
                "  `remark` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '备注',\n" +
                "  `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0 否，1 是',\n" +
                "  `creator` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '创建者',\n" +
                "  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                "  `updater` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '更新者',\n" +
                "  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',\n" +
                "  PRIMARY KEY (`id`) USING BTREE,\n" +
                "  INDEX `idx_source_table_source_id` (`source_table`, `source_id`) USING BTREE\n" +
                ") ENGINE=InnoDB COMMENT='附件表'";
    }

    @Test
    public void testParseToWord() {
        String ddl = getDDL();
        List<DDLWord> words = DDLParser.parseToWord(ddl);
        words.forEach(System.out::println);
    }

    @Test
    public void testParse() {
        String ddl = getDDL();
        Table table = DDLParser.parse(ddl);
        System.out.println("表名：" + table.getName());
        System.out.println("表说明：" + table.getComment());
        System.out.println("字段数：" + table.getColumns().size());
        System.out.println("字段列表：");
        table.getColumns().forEach(System.out::println);
    }

}
