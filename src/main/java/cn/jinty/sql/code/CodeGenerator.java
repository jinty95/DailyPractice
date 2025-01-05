package cn.jinty.sql.code;

import cn.jinty.exception.ValidateException;
import cn.jinty.sql.ddl.DDLParser;
import cn.jinty.sql.entity.Column;
import cn.jinty.sql.entity.Table;
import cn.jinty.sql.mapper.MysqlTypeMapper;
import cn.jinty.sql.mapper.TypeMapper;
import cn.jinty.sql.validate.TableValidation;
import cn.jinty.util.DateUtil;
import cn.jinty.util.JdbcUtil;
import cn.jinty.util.collection.CollectionUtil;
import cn.jinty.util.collection.MapUtil;
import cn.jinty.util.io.FilePathUtil;
import cn.jinty.util.io.FileUtil;
import cn.jinty.util.object.ObjectUtil;
import cn.jinty.util.string.NameStringUtil;
import cn.jinty.util.string.StringUtil;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

import static cn.jinty.sql.code.TemplatePlaceholderEnum.*;

/**
 * 代码生成器
 *
 * @author Jinty
 * @date 2023/3/6
 **/
public class CodeGenerator {

    // 解析配置文件
    private static Properties props;

    static {
        try {
            Properties choice = FileUtil.parseProperties(new File(
                    FilePathUtil.getAbsolutePath("/properties/codegen/codegen-choice.properties")));
            String choiceConfig = choice.getProperty("choice");
            props = FileUtil.parseProperties(new File(
                    FilePathUtil.getAbsolutePath("/properties/codegen/" + choiceConfig)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据配置指定的数据库连接和表，批量生成代码文件
     *
     * @throws Exception 异常
     */
    public static void batchGenerate() throws Exception {
        long begin = System.currentTimeMillis();
        String targetTableNames = props.getProperty("targetTableNames");
        Set<String> targetTableNameSet = new HashSet<>();
        if (StringUtil.isNotBlank(targetTableNames) && !"*".equals(targetTableNames)) {
            targetTableNameSet = new HashSet<>(Arrays.asList(targetTableNames.split(",")));
        }
        int cnt = 0;
        // 从数据库获取所有DDL
        List<String> ddlList = null;
        String driver = props.getProperty("db.driver");
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        try (Connection conn = JdbcUtil.getConnection(driver, url, user, password)) {
            ddlList = JdbcUtil.getAllCreateTable(conn);
        }
        if (CollectionUtil.isEmpty(ddlList)) {
            System.out.printf("代码生成结束，找不到ddl，未生成任何代码文件%n");
            return;
        }
        // 循环生成每个DDL对应的代码文件
        for (String ddl : ddlList) {
            Table table = DDLParser.parse(ddl);
            if (targetTableNameSet.isEmpty() || targetTableNameSet.contains(table.getName())) {
                generate(ddl);
                cnt++;
            }
        }
        long end = System.currentTimeMillis();
        System.out.printf("代码生成结束，一共生成%s个表的对应代码，耗时%s毫秒%n", cnt, (end - begin));
    }

    /**
     * 根据DDL及模板文件，生成代码文件
     *
     * @param ddl DDL
     * @throws IOException IO异常
     */
    public static void generate(String ddl) throws IOException {
        // 指定类型映射
        TypeMapper typeMapper = new MysqlTypeMapper();
        // 指定包名及作者
        Map<String, String> data = new HashMap<>();
        data.put(BASE_PACKAGE.name(), props.getProperty("basePackage"));
        data.put(AUTHOR.name(), props.getProperty("author"));
        // 指定末端包名
        data.put(END_PACKAGE_ENTITY.name(), props.getProperty("endPackage.entity"));
        data.put(END_PACKAGE_XML.name(), props.getProperty("endPackage.xml"));
        data.put(END_PACKAGE_MAPPER.name(), props.getProperty("endPackage.mapper"));
        data.put(END_PACKAGE_SERVICE.name(), props.getProperty("endPackage.service"));
        data.put(END_PACKAGE_SERVICE_IMPL.name(), props.getProperty("endPackage.serviceImpl"));
        data.put(END_PACKAGE_XML_EXT.name(), props.getProperty("endPackage.xmlExt"));
        data.put(END_PACKAGE_MAPPER_EXT.name(), props.getProperty("endPackage.mapperExt"));
        // 指定末端名称
        data.put(END_NAME_ENTITY.name(), props.getProperty("endName.entity"));
        data.put(END_NAME_XML.name(), props.getProperty("endName.xml"));
        data.put(END_NAME_MAPPER.name(), props.getProperty("endName.mapper"));
        data.put(END_NAME_SERVICE.name(), props.getProperty("endName.service"));
        data.put(END_NAME_SERVICE_IMPL.name(), props.getProperty("endName.serviceImpl"));
        data.put(END_NAME_XML_EXT.name(), props.getProperty("endName.xmlExt"));
        data.put(END_NAME_MAPPER_EXT.name(), props.getProperty("endName.mapperExt"));
        // 指定校验数据
        TableValidation validation = TableValidation.parseFromProps(props);
        // 指定生成哪些文件
        for (String type : props.getProperty("genType").split(",")) {
            // 指定模板路径
            String templateFilePath = FilePathUtil.getAbsolutePath(
                    props.getProperty("relativeTemplateFilePath." + type));
            // 指定目标目录
            String targetDir = FilePathUtil.concatBySeparator(props.getProperty("baseTargetDir"),
                    ObjectUtil.firstNotNull(props.getProperty("basePackage"), "").replace(".", "/"),
                    ObjectUtil.firstNotNull(props.getProperty("endPackage." + type), "").replace(".", "/")
            );
            // 指定文件后缀
            String targetFileSuffix = ObjectUtil.firstNotNull(props.getProperty("endName." + type), "")
                    + props.getProperty("fileNameSuffix." + type);
            // 生成文件
            generate(ddl, typeMapper, validation, data, templateFilePath, targetDir, targetFileSuffix);
        }
    }

    /* 以下为内部函数 */

    /**
     * 根据DDL及模板文件，生成代码文件
     *
     * @param ddl              DDL
     * @param typeMapper       类型映射
     * @param validation       校验数据
     * @param data             用于填充模板占位符的数据
     * @param templateFilePath 模板文件路径
     * @param targetDir        目标文件目录
     * @param targetFileSuffix 目标文件后缀
     * @throws IOException IO异常
     */
    private static void generate(String ddl, TypeMapper typeMapper, TableValidation validation, Map<String, String> data,
                                 String templateFilePath, String targetDir, String targetFileSuffix) throws IOException {
        // 解析DDL
        Table table = DDLParser.parse(ddl);
        // 校验DDL
        validateTable(table, validation);
        // 准备数据
        List<Map<String, String>> columnData = new ArrayList<>();
        prepareData(table, typeMapper, data, columnData);
        // 替换数据
        String template = FileUtil.read(new File(templateFilePath));
        template = fillWithData(template, data, columnData);
        // 生成文件
        String className = data.getOrDefault(CLASS_NAME.name(), "");
        String targetFilePath = FilePathUtil.concatBySeparator(targetDir, className + targetFileSuffix);
        FileUtil.write(template, new File(targetFilePath));
        System.out.printf("根据DDL及模板文件，生成代码文件成功：\ntemplateFilePath=%s\ntargetFilePath=%s\n\n",
                templateFilePath, targetFilePath);
    }

    /**
     * 向模板占位符填充数据
     *
     * @param template   带有占位符模板内容
     * @param data       基本数据(直接替换)
     * @param columnData 字段数据(循环替换)
     */
    private static String fillWithData(String template, Map<String, String> data, List<Map<String, String>> columnData) {
        List<TemplatePlaceholderEnum> columnPlaceholderEnums = TemplatePlaceholderEnum.fieldAndColumn();
        // 1、直接替换
        for (TemplatePlaceholderEnum placeholderEnum : TemplatePlaceholderEnum.values()) {
            // 排除循环替换的部分
            if (columnPlaceholderEnums.contains(placeholderEnum)) {
                continue;
            }
            String value = data.get(placeholderEnum.name());
            String placeholder = String.format("${%s}", placeholderEnum.name());
            if (value != null && template.contains(placeholder)) {
                template = template.replace(placeholder, value);
            }
        }
        // 2、循环替换
        String foreachPlaceholder = String.format("${%s}", FOR_EACH.name());
        String endForeachPlaceholder = String.format("${%s}", END_FOR_EACH.name());
        // foreach定位
        int foreach = template.indexOf(foreachPlaceholder);
        int endForeach = template.indexOf(endForeachPlaceholder);
        // foreach可能有多个，按顺序逐个处理
        while (foreach != -1 && endForeach != -1 && foreach < endForeach) {
            // foreach内容提取
            String content = template.substring(foreach + foreachPlaceholder.length(), endForeach);
            // foreach内容处理
            StringBuilder contentList = new StringBuilder();
            for (Map<String, String> columnMap : columnData) {
                String replaceContent = content;
                for (TemplatePlaceholderEnum columnPlaceholderEnum : columnPlaceholderEnums) {
                    String value = columnMap.get(columnPlaceholderEnum.name());
                    String columnPlaceholder = String.format("${%s}", columnPlaceholderEnum.name());
                    if (value != null && replaceContent.contains(columnPlaceholder)) {
                        replaceContent = replaceContent.replace(columnPlaceholder, value);
                    }
                }
                contentList.append(replaceContent);
            }
            // foreach占位符替换为以上内容
            template = template.substring(0, foreach) + contentList
                    + template.substring(endForeach + endForeachPlaceholder.length());
            // 寻找下一个foreach
            foreach = template.indexOf(foreachPlaceholder);
            endForeach = template.indexOf(endForeachPlaceholder);
        }
        return template;
    }

    /**
     * 解析DDL，构造用于填充模板占位符的数据
     *
     * @param table      表结构
     * @param typeMapper 类型映射
     * @param data       基本数据(直接替换)
     * @param columnData 字段数据(循环替换)
     */
    private static void prepareData(Table table, TypeMapper typeMapper,
                                    Map<String, String> data, List<Map<String, String>> columnData) {
        List<Column> columnList = table.getColumns();
        StringBuilder importClass = new StringBuilder();
        Set<Class<?>> alreadyImport = new HashSet<>();
        // 构造字段数据(不包括主键)
        for (int i = 0; i < columnList.size(); i++) {
            Column column = columnList.get(i);
            Class<?> fieldClass = typeMapper.sqlTypeToJavaClass(column.getType());
            String fieldName = NameStringUtil.snakeToCamel(column.getName(), false);
            if (!column.getIsPrimaryKey()) {
                Map<String, String> columnMap = new HashMap<>();
                columnMap.put(COLUMN_NAME.name(), column.getName());
                columnMap.put(COLUMN_TYPE.name(), column.getType());
                columnMap.put(COLUMN_DEFAULT.name(), column.getDefaultValue());
                columnMap.put(COLUMN_COMMENT.name(), column.getComment());
                columnMap.put(FIELD_CLASS.name(), fieldClass.getName());
                columnMap.put(FIELD_TYPE.name(), fieldClass.getSimpleName());
                columnMap.put(FIELD_NAME.name(), fieldName);
                columnMap.put(FIELD_NAME_UPPER_FIRST.name(), StringUtil.upperFirst(fieldName));
                columnMap.put(FIELD_SORT_NO.name(), String.valueOf(i + 1));
                columnData.add(columnMap);
            }
            // 判断字段类型是否需要导入
            if (!fieldClass.getName().startsWith("java.lang") && !alreadyImport.contains(fieldClass)) {
                alreadyImport.add(fieldClass);
                importClass.append(String.format("import %s;\n", fieldClass.getName()));
            }
        }
        // 构造基本数据
        data.put(DATE.name(), DateUtil.format(new Date(), DateUtil.DateFormat.DATE_1.getFormat()));
        data.put(IMPORT_CLASS.name(), importClass.toString().trim());
        data.put(CLASS_NAME.name(), NameStringUtil.snakeToCamel(table.getName(), true));
        data.put(TABLE_NAME.name(), table.getName());
        data.put(TABLE_COMMENT.name(), table.getComment());
        for (Column column : columnList) {
            if (column.getIsPrimaryKey()) {
                data.put(PK_COLUMN_NAME.name(), column.getName());
                data.put(PK_COLUMN_TYPE.name(), column.getType());
                data.put(PK_COLUMN_COMMENT.name(), column.getComment());
                Class<?> pkFieldClass = typeMapper.sqlTypeToJavaClass(column.getType());
                String pkFieldName = NameStringUtil.snakeToCamel(column.getName(), false);
                data.put(PK_FIELD_CLASS.name(), pkFieldClass.getName());
                data.put(PK_FIELD_TYPE.name(), pkFieldClass.getSimpleName());
                data.put(PK_FIELD_NAME.name(), pkFieldName);
                data.put(PK_FIELD_NAME_UPPER_FIRST.name(), StringUtil.upperFirst(pkFieldName));
                break;
            }
        }
    }

    /**
     * 校验表结构 (当前只校验特定字段的默认值是否符合要求)
     *
     * @param table      表结构
     * @param validation 校验数据
     */
    private static void validateTable(Table table, TableValidation validation) {
        if (validation == null) {
            return;
        }
        Map<String, Set<String>> defaultValueMap = validation.getDefaultValue();
        if (MapUtil.isEmpty(defaultValueMap)) {
            return;
        }
        for (Column column : table.getColumns()) {
            if (!defaultValueMap.containsKey(column.getName())) {
                continue;
            }
            Set<String> options = defaultValueMap.get(column.getName());
            if (!options.contains(String.valueOf(column.getDefaultValue()))) {
                String error = String.format("表[%s]字段[%s]默认值[%s]不符合要求，可选默认值[%s]",
                        table.getName(), column.getName(), column.getDefaultValue(), options);
                if (validation.getTerminateForFail()) {
                    throw new ValidateException(error);
                }
                System.err.println(error);
            }
        }
    }

}
