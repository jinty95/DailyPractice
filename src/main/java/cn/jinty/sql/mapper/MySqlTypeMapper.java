package cn.jinty.sql.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * MySql类型映射器
 *
 * @author Jinty
 * @date 2023/3/6
 **/
public class MySqlTypeMapper implements TypeMapper {

    private static final Map<String, String> SQL_TO_JAVA = new HashMap<>();
    private static final Map<String, String> JAVA_TO_SQL = new HashMap<>();

    static {
        SQL_TO_JAVA.put("BIT", Boolean.class.getSimpleName());

        SQL_TO_JAVA.put("TINYINT", Integer.class.getSimpleName());
        SQL_TO_JAVA.put("SMALLINT", Integer.class.getSimpleName());
        SQL_TO_JAVA.put("MEDIUMINT", Integer.class.getSimpleName());
        SQL_TO_JAVA.put("INT", Integer.class.getSimpleName());
        SQL_TO_JAVA.put("BIGINT", Long.class.getSimpleName());

        SQL_TO_JAVA.put("FLOAT", Float.class.getSimpleName());
        SQL_TO_JAVA.put("DOUBLE", Double.class.getSimpleName());
        SQL_TO_JAVA.put("DECIMAL", BigDecimal.class.getSimpleName());

        SQL_TO_JAVA.put("DATE", Date.class.getSimpleName());
        SQL_TO_JAVA.put("TIME", Date.class.getSimpleName());
        SQL_TO_JAVA.put("YEAR", Date.class.getSimpleName());
        SQL_TO_JAVA.put("DATETIME", Date.class.getSimpleName());
        SQL_TO_JAVA.put("TIMESTAMP", Date.class.getSimpleName());

        SQL_TO_JAVA.put("CHAR", String.class.getSimpleName());
        SQL_TO_JAVA.put("VARCHAR", String.class.getSimpleName());
        SQL_TO_JAVA.put("TINYTEXT", String.class.getSimpleName());
        SQL_TO_JAVA.put("TEXT", String.class.getSimpleName());
        SQL_TO_JAVA.put("MEDIUMTEXT", String.class.getSimpleName());
        SQL_TO_JAVA.put("LONGTEXT", String.class.getSimpleName());
        SQL_TO_JAVA.put("JSON", String.class.getSimpleName());

        for (Map.Entry<String, String> entry : SQL_TO_JAVA.entrySet()) {
            JAVA_TO_SQL.put(entry.getValue(), entry.getKey());
        }
    }

    @Override
    public String sqlTypeToJavaType(String sqlType) {
        return SQL_TO_JAVA.getOrDefault(sqlType.toUpperCase(), String.class.getSimpleName());
    }

    @Override
    public String javaTypeToSqlType(String javaType) {
        return JAVA_TO_SQL.getOrDefault(javaType, "VARCHAR");
    }

}
