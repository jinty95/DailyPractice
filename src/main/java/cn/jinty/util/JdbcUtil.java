package cn.jinty.util;

import cn.jinty.util.io.FileUtil;
import cn.jinty.util.object.BeanUtil;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * JDBC - 工具类
 *
 * @author Jinty
 * @date 2023/3/16
 **/
public final class JdbcUtil {

    private JdbcUtil() {
    }

    /**
     * 获取默认数据库连接
     *
     * @return 数据库连接
     * @throws IOException            解析配置失败
     * @throws ClassNotFoundException 驱动类找不到
     * @throws SQLException           连接失败
     */
    public static Connection getDefaultConnection() throws IOException, SQLException, ClassNotFoundException {
        Properties properties = FileUtil.parseProperties(
                FileUtil.getAbsolutePath("/properties/application.properties", true));
        String driver = properties.getProperty("db.driver");
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        return getConnection(driver, url, user, password);
    }

    /**
     * 获取数据库连接
     *
     * @param driver   驱动类
     * @param url      路径
     * @param user     账号
     * @param password 密码
     * @return 数据库连接
     * @throws ClassNotFoundException 驱动类找不到
     * @throws SQLException           连接失败
     */
    public static Connection getConnection(String driver, String url, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 解析结果集
     *
     * @param resultSet 原始结果集
     * @return 字段和值的映射列表
     * @throws SQLException SQL异常
     */
    public static List<Map<String, String>> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Map<String, String>> mapList = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()) {
            Map<String, String> map = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                map.put(metaData.getColumnLabel(i), resultSet.getString(i));
            }
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 解析结果集
     *
     * @param resultSet  原始结果集
     * @param resultType 结果对象类型
     * @param <T>        结果对象类型
     * @return 结果对象列表
     * @throws Exception 异常
     */
    public static <T> List<T> parseResultSet(ResultSet resultSet, Class<T> resultType) throws Exception {
        return parseResultSet(resultSet, resultType, true);
    }

    /**
     * 解析结果集
     *
     * @param resultSet    原始结果集
     * @param resultType   结果对象类型
     * @param snakeToCamel 是否下划线转驼峰 (一般数据库字段都是下划线命名，Java字段都是驼峰命名)
     * @param <T>          结果对象类型
     * @return 结果对象列表
     * @throws Exception 异常
     */
    public static <T> List<T> parseResultSet(ResultSet resultSet, Class<T> resultType, boolean snakeToCamel) throws Exception {
        List<Map<String, String>> mapList = parseResultSet(resultSet);
        List<T> objList = new ArrayList<>();
        for (Map<String, String> map : mapList) {
            if (snakeToCamel) {
                Map<String, String> camelMap = new HashMap<>();
                for (String column : map.keySet()) {
                    camelMap.put(StringUtil.snakeToCamel(column, false), map.get(column));
                }
                objList.add(BeanUtil.mapToBean(camelMap, resultType));
            } else {
                objList.add(BeanUtil.mapToBean(map, resultType));
            }
        }
        return objList;
    }

    /**
     * 执行查询语句
     *
     * @param conn 连接
     * @param sql  查询语句
     * @return 查询结果
     * @throws SQLException SQL异常
     */
    public static List<Map<String, String>> executeQuery(Connection conn, String sql) throws SQLException {
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            return parseResultSet(resultSet);
        }
    }

    /**
     * 执行查询语句
     *
     * @param conn        连接
     * @param preparedSql 预编译查询语句 (以?为占位符)
     * @param params      参数列表
     * @return 查询结果
     * @throws SQLException SQL异常
     */
    public static List<Map<String, String>> executeQuery(Connection conn, String preparedSql, List<Object> params) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(preparedSql)) {
            for (int i = 1; i <= params.size(); i++) {
                statement.setObject(i, params.get(i - 1));
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                return parseResultSet(resultSet);
            }
        }
    }

    /**
     * 执行查询语句
     *
     * @param conn       连接
     * @param sql        查询语句
     * @param resultType 结果对象类型
     * @param <T>        结果对象类型
     * @return 结果对象列表
     * @throws Exception 异常
     */
    public static <T> List<T> executeQuery(Connection conn, String sql, Class<T> resultType) throws Exception {
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            return parseResultSet(resultSet, resultType);
        }
    }

    /**
     * 执行查询语句
     *
     * @param conn        连接
     * @param preparedSql 预编译查询语句 (以?为占位符)
     * @param params      参数列表
     * @param resultType  结果对象类型
     * @param <T>         结果对象类型
     * @return 结果对象列表
     * @throws Exception 异常
     */
    public static <T> List<T> executeQuery(Connection conn, String preparedSql, List<Object> params, Class<T> resultType) throws Exception {
        try (PreparedStatement statement = conn.prepareStatement(preparedSql)) {
            for (int i = 1; i <= params.size(); i++) {
                statement.setObject(i, params.get(i - 1));
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                return parseResultSet(resultSet, resultType);
            }
        }
    }

    /**
     * 执行变更语句 (DML/DDL)
     *
     * @param conn 连接
     * @param sql  变更语句
     * @return 影响行数
     * @throws SQLException SQL异常
     */
    public static int executeUpdate(Connection conn, String sql) throws SQLException {
        try (Statement statement = conn.createStatement()) {
            return statement.executeUpdate(sql);
        }
    }

    /**
     * 执行变更语句 (DML/DDL)
     *
     * @param conn        连接
     * @param preparedSql 预编译变更语句 (以?为占位符)
     * @param params      参数列表
     * @return 影响行数
     * @throws SQLException SQL异常
     */
    public static int executeUpdate(Connection conn, String preparedSql, List<Object> params) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(preparedSql)) {
            for (int i = 1; i <= params.size(); i++) {
                statement.setObject(i, params.get(i - 1));
            }
            return statement.executeUpdate();
        }
    }

    /**
     * 执行批量变更语句 (DML/DDL)
     *
     * @param conn    连接
     * @param sqlList 多个变更语句
     * @return 各个语句的影响行数
     * @throws SQLException SQL异常
     */
    public static int[] executeBatch(Connection conn, List<String> sqlList) throws SQLException {
        try (Statement statement = conn.createStatement()) {
            for (String sql : sqlList) {
                statement.addBatch(sql);
            }
            return statement.executeBatch();
        }
    }

    /**
     * 执行批量变更语句 (DML/DDL)
     *
     * @param conn        连接
     * @param preparedSql 预编译变更语句 (以?为占位符)
     * @param params      多个参数列表
     * @return 各个语句的影响行数
     * @throws SQLException SQL异常
     */
    public static int[] executeBatch(Connection conn, String preparedSql, List<List<Object>> params) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(preparedSql)) {
            for (List<Object> param : params) {
                for (int i = 1; i <= param.size(); i++) {
                    statement.setObject(i, param.get(i - 1));
                }
                statement.addBatch();
            }
            return statement.executeBatch();
        }
    }

    /**
     * 获取数据库所有的建表语句
     *
     * @param conn 数据库连接
     * @return 所有的建表语句
     * @throws SQLException SQL异常
     */
    public static List<String> getAllCreateTable(Connection conn) throws SQLException {
        List<String> ddlList = new ArrayList<>();
        String sql = "show tables";
        List<Map<String, String>> tables = JdbcUtil.executeQuery(conn, sql);
        for (Map<String, String> table : tables) {
            sql = String.format("show create table `%s`", table.entrySet().iterator().next().getValue());
            List<Map<String, String>> createTable = JdbcUtil.executeQuery(conn, sql);
            ddlList.add(createTable.get(0).get("Create Table"));
        }
        return ddlList;
    }

}
