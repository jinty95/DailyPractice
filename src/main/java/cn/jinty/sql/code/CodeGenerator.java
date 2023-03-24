package cn.jinty.sql.code;

import cn.jinty.sql.ddl.DDLParser;
import cn.jinty.sql.entity.Column;
import cn.jinty.sql.entity.Table;
import cn.jinty.sql.mapper.TypeMapper;
import cn.jinty.util.DateUtil;
import cn.jinty.util.StringUtil;
import cn.jinty.util.io.FileUtil;

import java.io.IOException;
import java.util.*;

import static cn.jinty.sql.code.TemplatePlaceholderEnum.*;

/**
 * 代码生成器
 *
 * @author Jinty
 * @date 2023/3/6
 **/
public class CodeGenerator {

    /**
     * 根据DDL及模板文件，生成代码文件
     *
     * @param ddl              DDL
     * @param typeMapper       类型映射
     * @param data             用于填充模板占位符的数据
     * @param templateFilePath 模板文件路径
     * @param targetDir        目标文件目录
     * @param targetFileSuffix 目标文件后缀
     * @throws IOException IO异常
     */
    public static void generate(String ddl, TypeMapper typeMapper, Map<String, String> data,
                                String templateFilePath, String targetDir, String targetFileSuffix) throws IOException {
        prepareData(ddl, typeMapper, data);
        String template = FileUtil.read(templateFilePath);
        template = fillWithData(template, data);
        String className = data.getOrDefault(CLASS_NAME.name(), "");
        String targetFilePath = FileUtil.concatBySeparator(targetDir, className + targetFileSuffix);
        FileUtil.write(template, targetFilePath);
        System.out.println(String.format("根据DDL及模板文件，生成代码文件成功：\ntemplateFilePath=%s\ntargetFilePath=%s\n",
                templateFilePath, targetFilePath));
    }

    /* 以下为内部函数 */

    /**
     * 向模板占位符填充数据
     *
     * @param template 带有占位符模板内容
     * @param data     用于填充模板占位符的数据
     */
    private static String fillWithData(String template, Map<String, String> data) {
        for (TemplatePlaceholderEnum placeholder : TemplatePlaceholderEnum.values()) {
            String value = data.get(placeholder.name());
            if (value != null) {
                template = template.replace(String.format("${%s}", placeholder.name()), value);
            }
        }
        return template;
    }

    /**
     * 解析DDL，构造用于填充模板占位符的数据
     *
     * @param ddl        DDL
     * @param typeMapper 类型映射
     * @param data       用于填充模板占位符的数据
     */
    private static void prepareData(String ddl, TypeMapper typeMapper, Map<String, String> data) {

        // 解析DDL
        Table table = DDLParser.parse(ddl);
        List<Column> columnList = table.getColumns();

        // 构造用于替换模板占位符的数据
        data.put(DATE.name(), DateUtil.format(new Date(), DateUtil.FORMAT_DATE_1));
        data.put(TABLE_NAME.name(), table.getName());
        data.put(TABLE_COMMENT.name(), table.getComment());
        data.put(CLASS_NAME.name(), StringUtil.snakeToCamel(table.getName(), true));

        StringBuilder classFields = new StringBuilder();
        StringBuilder importClass = new StringBuilder();
        Set<Class<?>> alreadyImport = new HashSet<>();
        for (Column column : columnList) {
            Class<?> fieldClass = typeMapper.sqlTypeToJavaClass(column.getType());
            String fieldType = fieldClass.getSimpleName();
            String fieldName = StringUtil.snakeToCamel(column.getName(), false);
            classFields.append("\n");
            classFields.append("    // ").append(column.getComment()).append("\n");
            classFields.append(String.format("    private %s %s;\n", fieldType, fieldName));
            if (!fieldClass.getName().startsWith("java.lang") && !alreadyImport.contains(fieldClass)) {
                alreadyImport.add(fieldClass);
                importClass.append(String.format("import %s;\n", fieldClass.getName()));
            }
        }
        data.put(CLASS_FIELDS.name(), classFields.toString().trim());
        data.put(IMPORT_CLASS.name(), importClass.toString().trim());

        for (Column column : columnList) {
            if (column.getIsPrimaryKey()) {
                data.put(PK_COLUMN_NAME.name(), column.getName());
                data.put(PK_FIELD_NAME.name(), StringUtil.snakeToCamel(column.getName(), false));
                Class<?> pkFiledClass = typeMapper.sqlTypeToJavaClass(column.getType());
                data.put(PK_FIELD_CLASS.name(), pkFiledClass.getName());
                data.put(PK_FIELD_TYPE.name(), pkFiledClass.getSimpleName());
                break;
            }
        }

        StringBuilder tableColumns = new StringBuilder();
        StringBuilder sqlResultMap = new StringBuilder();
        StringBuilder sqlSelectCondition = new StringBuilder();
        StringBuilder sqlInsertDefaultColumns = new StringBuilder();
        StringBuilder sqlInsertDefaultValues = new StringBuilder();
        StringBuilder sqlInsertColumns = new StringBuilder();
        StringBuilder sqlInsertValues = new StringBuilder();
        StringBuilder sqlBatchInsertColumns = new StringBuilder();
        StringBuilder sqlBatchInsertValues = new StringBuilder();
        StringBuilder sqlUpdateColumns = new StringBuilder();

        sqlBatchInsertValues.append("<foreach item=\"item\" collection=\"list\" index=\"index\" separator=\",\">\n            ");
        sqlBatchInsertValues.append("(\n            ");

        for (int i = 0; i < columnList.size(); i++) {
            Column column = columnList.get(i);
            String columnName = column.getName();
            String propertyName = StringUtil.snakeToCamel(columnName, false);
            String defaultValue = column.getDefaultValue();
            String commaOrNot = i < columnList.size() - 1 ? ", " : "";
            boolean changeLine = (i + 1) % 8 == 0;

            if (column.getIsPrimaryKey()) {
                sqlResultMap.append(String.format("<id column=\"%s\" property=\"%s\"/>\n        ", columnName, propertyName));
            } else {
                sqlResultMap.append(String.format("<result column=\"%s\" property=\"%s\"/>\n        ", columnName, propertyName));
            }

            tableColumns.append('`').append(columnName).append('`').append(commaOrNot).append(changeLine ? "\n        " : "");

            sqlSelectCondition.append(String.format("<if test=\"%s != null\"> and `%s` = #{%s} </if>\n        ", propertyName, columnName, propertyName));

            if (column.getIsPrimaryKey() && column.getIsAutoIncrement()) {
                data.put(SQL_INSERT_GEN_KEY.name(), String.format("useGeneratedKeys=\"true\" keyProperty=\"%s\"", propertyName));
            } else {
                sqlInsertDefaultColumns.append('`').append(columnName).append('`').append(commaOrNot).append(changeLine ? "\n            " : "");

                sqlInsertDefaultValues.append(defaultValue).append(commaOrNot).append(changeLine ? "\n            " : "");

                sqlInsertColumns.append(String.format("<if test=\"%s != null\">`%s`,</if>\n            ", propertyName, columnName));

                sqlInsertValues.append(String.format("<if test=\"%s != null\">#{%s},</if>\n            ", propertyName, propertyName));

                sqlBatchInsertColumns.append('`').append(columnName).append('`').append(commaOrNot).append(changeLine ? "\n            " : "");

                sqlBatchInsertValues.append(String.format("<choose><when test=\"item.%s != null\">#{item.%s}%s</when><otherwise>%s%s</otherwise></choose>\n            ",
                        propertyName, propertyName, commaOrNot, defaultValue, commaOrNot));
            }

            if (!column.getIsPrimaryKey()) {
                sqlUpdateColumns.append(String.format("<if test=\"%s != null\"> `%s` = #{%s}, </if>\n            ", propertyName, columnName, propertyName));
            }

        }

        sqlBatchInsertValues.append(")\n        ");
        sqlBatchInsertValues.append("</foreach>");

        data.put(TABLE_COLUMNS.name(), tableColumns.toString().trim());
        data.put(SQL_RESULT_MAP.name(), sqlResultMap.toString().trim());
        data.put(SQL_SELECT_CONDITION.name(), sqlSelectCondition.toString().trim());
        data.put(SQL_INSERT_DEFAULT_COLUMNS.name(), sqlInsertDefaultColumns.toString().trim());
        data.put(SQL_INSERT_DEFAULT_VALUES.name(), sqlInsertDefaultValues.toString().trim());
        data.put(SQL_INSERT_COLUMNS.name(), sqlInsertColumns.toString().trim());
        data.put(SQL_INSERT_VALUES.name(), sqlInsertValues.toString().trim());
        data.put(SQL_BATCH_INSERT_COLUMNS.name(), sqlBatchInsertColumns.toString().trim());
        data.put(SQL_BATCH_INSERT_VALUES.name(), sqlBatchInsertValues.toString().trim());
        data.put(SQL_UPDATE_COLUMNS.name(), sqlUpdateColumns.toString().trim());

    }

}
