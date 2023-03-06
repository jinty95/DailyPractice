package test.cn.jinty.sql.code;

import cn.jinty.Main;
import cn.jinty.sql.code.CodeGenerator;
import cn.jinty.sql.mapper.MySqlTypeMapper;
import cn.jinty.sql.mapper.TypeMapper;
import cn.jinty.util.io.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static cn.jinty.sql.code.JavaEntityTemplatePlaceholderEnum.*;

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
            // 从文件读取DDL
            URL url = Main.class.getResource("/sql/base_table.sql");
            assert url != null;
            String ddlFilePath = FileUtil.convertSeparator(url.getPath());
            System.out.println("DDL文件路径：" + ddlFilePath);
            String ddl = FileUtil.read(ddlFilePath);
            // 获取模板路径
            url = Main.class.getResource("/template/java_entity_template.txt");
            assert url != null;
            String templateFilePath = FileUtil.convertSeparator(url.getPath());
            System.out.println("模板文件路径：" + templateFilePath);
            // 指定输出文件目录
            String targetDir = FileUtil.convertSeparator("D:/temp/codegen");
            System.out.println("输出目录路径：" + targetDir);
            // 生成Java文件
            TypeMapper typeMapper = new MySqlTypeMapper();
            Map<String, String> valueMapper = new HashMap<>();
            valueMapper.put(PACKAGE_NAME.name(), "cn.jinty.entity");
            valueMapper.put(AUTHOR.name(), "Jinty");
            CodeGenerator.genJavaEntity(ddl, typeMapper, valueMapper, templateFilePath, targetDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
