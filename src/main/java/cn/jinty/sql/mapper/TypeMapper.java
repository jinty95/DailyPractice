package cn.jinty.sql.mapper;

/**
 * 类型映射器
 *
 * @author Jinty
 * @date 2023/3/6
 **/
public interface TypeMapper {

    /**
     * 数据库类型 -> Java类型
     *
     * @param sqlType 数据库类型
     * @return Java类型
     */
    Class<?> sqlTypeToJavaClass(String sqlType);

    /**
     * 数据库类型 -> Java类型
     *
     * @param sqlType 数据库类型
     * @return Java类型
     */
    String sqlTypeToJavaType(String sqlType);

    /**
     * Java类型 -> 数据库类型
     *
     * @param javaType Java类型
     * @return 数据库类型
     */
    String javaTypeToSqlType(String javaType);

}
