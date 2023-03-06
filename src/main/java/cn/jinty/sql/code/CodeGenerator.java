package cn.jinty.sql.code;

import cn.jinty.sql.ddl.DDLParser;
import cn.jinty.sql.entity.Column;
import cn.jinty.sql.entity.Table;
import cn.jinty.sql.mapper.TypeMapper;
import cn.jinty.util.DateUtil;
import cn.jinty.util.StringUtil;
import cn.jinty.util.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import static cn.jinty.sql.code.JavaEntityTemplatePlaceholderEnum.*;

/**
 * 代码生成器
 *
 * @author Jinty
 * @date 2023/3/6
 **/
public class CodeGenerator {

    /**
     * 根据DDL生成Java实体类
     *
     * @param ddl              DDL
     * @param typeMapper       类型映射
     * @param valueMapper      值映射(用于替换模板的占位符)
     * @param templateFilePath 模板文件路径
     * @param targetDir        输出文件目录
     * @throws IOException IO异常
     */
    public static void genJavaEntity(String ddl, TypeMapper typeMapper, Map<String, String> valueMapper,
                                     String templateFilePath, String targetDir) throws IOException {
        // 解析DDL
        Table table = DDLParser.parse(ddl);
        // 提取类信息
        String className = StringUtil.snakeToCamel(table.getName(), true);
        String classDesc = table.getComment();
        String date = DateUtil.format(new Date(), DateUtil.FORMAT_DATE_1);
        StringBuilder fields = new StringBuilder();
        for (Column column : table.getColumns()) {
            // 通过类型映射确定字段类型
            String fieldType = typeMapper.sqlTypeToJavaType(column.getType());
            String fieldName = StringUtil.snakeToCamel(column.getName(), false);
            fields.append("\n");
            fields.append("    // ").append(column.getComment()).append("\n");
            fields.append(String.format("    private %s %s;\n", fieldType, fieldName));
        }
        // 写入值映射
        valueMapper.put(CLASS_NAME.name(), className);
        valueMapper.put(CLASS_DESC.name(), classDesc);
        valueMapper.put(DATE.name(), date);
        valueMapper.put(FIELDS.name(), fields.toString());
        // 获取模板，替换占位符
        String template = FileUtil.read(templateFilePath);
        for (JavaEntityTemplatePlaceholderEnum placeholder : JavaEntityTemplatePlaceholderEnum.values()) {
            String value = valueMapper.get(placeholder.name());
            if (value == null) {
                continue;
            }
            template = template.replace(String.format("${%s}", placeholder.name()), value);
        }
        // 输出到指定文件
        String packageName = valueMapper.getOrDefault(PACKAGE_NAME.name(), StringUtil.EMPTY);
        packageName = packageName.replace(".", File.separator);
        String targetFilePath = FileUtil.concatBySeparator(targetDir, packageName, className + ".java");
        FileUtil.write(template, targetFilePath);
        System.out.println(String.format("根据DDL生成Java实体类代码，执行成功：targetFilePath=%s", targetFilePath));
    }

}
