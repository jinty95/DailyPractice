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
import java.util.List;
import java.util.Map;

import static cn.jinty.sql.code.JavaEntityTemplatePlaceholderEnum.*;
import static cn.jinty.sql.code.MybatisXmlTemplatePlaceholderEnum.*;

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

        // 基于表信息渲染数据
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

    /**
     * 根据DDL生成Mybatis的XML文件
     *
     * @param ddl              DDL
     * @param typeMapper       类型映射
     * @param valueMapper      值映射(用于替换模板的占位符)
     * @param templateFilePath 模板文件路径
     * @param targetDir        输出文件目录
     * @throws IOException IO异常
     */
    public static void genMybatisXml(String ddl, TypeMapper typeMapper, Map<String, String> valueMapper,
                                     String templateFilePath, String targetDir) throws IOException {

        // 解析DDL
        Table table = DDLParser.parse(ddl);

        // 基于表信息渲染数据
        String entityPackageName = valueMapper.getOrDefault(ENTITY_PACKAGE_NAME.name(), StringUtil.EMPTY);
        String entityClassName = StringUtil.snakeToCamel(table.getName(), true);
        String entityFullName = entityPackageName + "." + entityClassName;

        String mapperPackageName = valueMapper.getOrDefault(MAPPER_PACKAGE_NAME.name(), StringUtil.EMPTY);
        String mapperClassName = entityClassName + "Mapper";
        String mapperFullName = mapperPackageName + "." + mapperClassName;

        StringBuilder resultMap = new StringBuilder();
        StringBuilder columns = new StringBuilder();
        StringBuilder selectCondition = new StringBuilder();
        StringBuilder insertColumns = new StringBuilder();
        StringBuilder insertValues = new StringBuilder();
        StringBuilder batchInsertColumns = new StringBuilder();
        StringBuilder batchInsertValues = new StringBuilder();
        StringBuilder updateColumns = new StringBuilder();

        batchInsertValues.append("<foreach item=\"item\" collection=\"list\" index=\"index\" separator=\",\">\n            ");
        batchInsertValues.append("(\n            ");

        List<Column> columnList = table.getColumns();
        for (int i = 0; i < columnList.size(); i++) {
            Column column = columnList.get(i);
            String columnName = column.getName();
            String propertyName = StringUtil.snakeToCamel(columnName, false);
            String defaultValue = column.getDefaultValue();
            String commaOrNot = i < columnList.size() - 1 ? ", " : "";
            boolean changeLine = (i + 1) % 8 == 0;

            if (column.getIsPrimaryKey()) {
                resultMap.append(String.format("<id column=\"%s\" property=\"%s\"/>\n        ", columnName, propertyName));
            } else {
                resultMap.append(String.format("<result column=\"%s\" property=\"%s\"/>\n        ", columnName, propertyName));
            }

            columns.append('`').append(columnName).append('`').append(commaOrNot).append(changeLine ? "\n        " : "");

            selectCondition.append(String.format("<if test=\"%s != null\"> and `%s` = #{%s} </if>\n        ", propertyName, columnName, propertyName));

            if (column.getIsPrimaryKey() && column.getIsAutoIncrement()) {
                valueMapper.put(INSERT_GEN_KEY.name(), String.format("useGeneratedKeys=\"true\" keyProperty=\"%s\"", propertyName));
            } else {
                insertColumns.append(String.format("<if test=\"%s != null\">`%s`,</if>\n            ", propertyName, columnName));

                insertValues.append(String.format("<if test=\"%s != null\">#{%s},</if>\n            ", propertyName, propertyName));

                batchInsertColumns.append('`').append(columnName).append('`').append(commaOrNot).append(changeLine ? "\n            " : "");

                batchInsertValues.append(String.format("<choose><when test=\"item.%s != null\">#{item.%s}%s</when><otherwise>%s%s</otherwise></choose>\n            ",
                        propertyName, propertyName, commaOrNot, defaultValue, commaOrNot));
            }

            if (column.getIsPrimaryKey()) {
                valueMapper.put(PK_PARAM_TYPE.name(), "java.lang." + typeMapper.sqlTypeToJavaType(column.getType()));
                valueMapper.put(PK_CONDITION.name(), String.format("`%s` = #{%s}", columnName, propertyName));
            } else {
                updateColumns.append(String.format("<if test=\"%s != null\"> `%s` = #{%s}, </if>\n            ", propertyName, columnName, propertyName));
            }

        }

        batchInsertValues.append(")\n        ");
        batchInsertValues.append("</foreach>");

        // 写入值映射
        valueMapper.put(MAPPER_FULL_NAME.name(), mapperFullName);
        valueMapper.put(ENTITY_FULL_NAME.name(), entityFullName);
        valueMapper.put(TABLE_NAME.name(), table.getName());
        valueMapper.put(RESULT_MAP.name(), resultMap.toString().trim());
        valueMapper.put(COLUMNS.name(), columns.toString().trim());
        valueMapper.put(SELECT_CONDITION.name(), selectCondition.toString().trim());
        valueMapper.put(INSERT_COLUMNS.name(), insertColumns.toString().trim());
        valueMapper.put(INSERT_VALUES.name(), insertValues.toString().trim());
        valueMapper.put(BATCH_INSERT_COLUMNS.name(), batchInsertColumns.toString().trim());
        valueMapper.put(BATCH_INSERT_VALUES.name(), batchInsertValues.toString().trim());
        valueMapper.put(UPDATE_COLUMNS.name(), updateColumns.toString().trim());

        // 获取模板，替换占位符
        String template = FileUtil.read(templateFilePath);
        for (MybatisXmlTemplatePlaceholderEnum placeholder : MybatisXmlTemplatePlaceholderEnum.values()) {
            String value = valueMapper.get(placeholder.name());
            if (value == null) {
                continue;
            }
            template = template.replace(String.format("${%s}", placeholder.name()), value);
        }

        // 输出到指定文件
        mapperPackageName = mapperPackageName.replace(".", File.separator);
        String targetFilePath = FileUtil.concatBySeparator(targetDir, mapperPackageName, mapperClassName + ".xml");
        FileUtil.write(template, targetFilePath);
        System.out.println(String.format("根据DDL生成Mybatis的XML文件，执行成功：targetFilePath=%s", targetFilePath));

    }

}
