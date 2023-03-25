package cn.jinty.sql.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Mysql类型映射器
 *
 * @author Jinty
 * @date 2023/3/6
 **/
public class MysqlTypeMapper implements TypeMapper {

    private static final Map<String, Class<?>> SQL_TYPE_TO_JAVA_CLASS = new HashMap<>();
    private static final Map<String, String> JAVA_TYPE_TO_SQL_TYPE = new HashMap<>();

    static {
        SQL_TYPE_TO_JAVA_CLASS.put("BIT", Boolean.class);

        SQL_TYPE_TO_JAVA_CLASS.put("TINYINT", Integer.class);
        SQL_TYPE_TO_JAVA_CLASS.put("SMALLINT", Integer.class);
        SQL_TYPE_TO_JAVA_CLASS.put("MEDIUMINT", Integer.class);
        SQL_TYPE_TO_JAVA_CLASS.put("INT", Integer.class);
        SQL_TYPE_TO_JAVA_CLASS.put("BIGINT", Long.class);

        SQL_TYPE_TO_JAVA_CLASS.put("FLOAT", Float.class);
        SQL_TYPE_TO_JAVA_CLASS.put("DOUBLE", Double.class);
        SQL_TYPE_TO_JAVA_CLASS.put("DECIMAL", BigDecimal.class);

        SQL_TYPE_TO_JAVA_CLASS.put("DATE", Date.class);
        SQL_TYPE_TO_JAVA_CLASS.put("TIME", Date.class);
        SQL_TYPE_TO_JAVA_CLASS.put("YEAR", Date.class);
        SQL_TYPE_TO_JAVA_CLASS.put("DATETIME", Date.class);
        SQL_TYPE_TO_JAVA_CLASS.put("TIMESTAMP", Date.class);

        SQL_TYPE_TO_JAVA_CLASS.put("CHAR", String.class);
        SQL_TYPE_TO_JAVA_CLASS.put("VARCHAR", String.class);
        SQL_TYPE_TO_JAVA_CLASS.put("TINYTEXT", String.class);
        SQL_TYPE_TO_JAVA_CLASS.put("TEXT", String.class);
        SQL_TYPE_TO_JAVA_CLASS.put("MEDIUMTEXT", String.class);
        SQL_TYPE_TO_JAVA_CLASS.put("LONGTEXT", String.class);
        SQL_TYPE_TO_JAVA_CLASS.put("JSON", String.class);

        for (Map.Entry<String, Class<?>> entry : SQL_TYPE_TO_JAVA_CLASS.entrySet()) {
            JAVA_TYPE_TO_SQL_TYPE.put(entry.getValue().getSimpleName(), entry.getKey());
        }
    }

    @Override
    public Class<?> sqlTypeToJavaClass(String sqlType) {
        return SQL_TYPE_TO_JAVA_CLASS.getOrDefault(sqlType.toUpperCase(), String.class);
    }

    @Override
    public String sqlTypeToJavaType(String sqlType) {
        return sqlTypeToJavaClass(sqlType).getSimpleName();
    }

    @Override
    public String javaTypeToSqlType(String javaType) {
        return JAVA_TYPE_TO_SQL_TYPE.getOrDefault(javaType, "VARCHAR");
    }

}
