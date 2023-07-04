package test.cn.jinty.util;

import cn.jinty.util.JdbcUtil;
import cn.jinty.util.io.FilePathUtil;
import cn.jinty.util.io.FileUtil;
import org.junit.Test;
import test.cn.jinty.sql.code.Job;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * JDBC - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/3/16
 **/
public class JdbcUtilTest {

    @Test
    public void executeQuery() {
        try (Connection conn = JdbcUtil.getDefaultConnection()) {
            String sql = "show tables";
            List<Map<String, String>> rows = JdbcUtil.executeQuery(conn, sql);
            System.out.println("sql: " + sql);
            System.out.println("result: " + rows.size());
            rows.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void executeQuery1() {
        try (Connection conn = JdbcUtil.getDefaultConnection()) {
            String sql = "select * from `job` where `is_deleted` = ? and `id` = ?";
            List<Object> params = Arrays.asList(0, 1);
            List<Map<String, String>> rows = JdbcUtil.executeQuery(conn, sql, params);
            System.out.println("sql: " + sql);
            System.out.println("params: " + params);
            System.out.println("result: " + rows.size());
            rows.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void executeQuery2() {
        try (Connection conn = JdbcUtil.getDefaultConnection()) {
            String sql = "select * from `job`";
            List<Job> jobList = JdbcUtil.executeQuery(conn, sql, Job.class);
            System.out.println("sql: " + sql);
            System.out.println("result: " + jobList.size());
            jobList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void executeQuery3() {
        try (Connection conn = JdbcUtil.getDefaultConnection()) {
            String sql = "select * from `job` where id >= ? and id <= ?";
            List<Object> params = Arrays.asList(1, 22);
            List<Job> jobList = JdbcUtil.executeQuery(conn, sql, params, Job.class);
            System.out.println("sql: " + sql);
            System.out.println("params: " + params);
            System.out.println("result: " + jobList.size());
            jobList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void executeUpdate() {
        try (Connection conn = JdbcUtil.getDefaultConnection()) {
            String sql = "update `job` set `updated_by` = 'xxx' where id = 1";
            int effect = JdbcUtil.executeUpdate(conn, sql);
            System.out.println("sql: " + sql);
            System.out.println("effect: " + effect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void executeUpdate1() {
        try (Connection conn = JdbcUtil.getDefaultConnection()) {
            String sql = "update `job` set `updated_by` = ? where id = ?";
            List<Object> params = Arrays.asList("yyy", 1);
            int effect = JdbcUtil.executeUpdate(conn, sql, params);
            System.out.println("sql: " + sql);
            System.out.println("params: " + params);
            System.out.println("effect: " + effect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void executeBatch() {
        try (Connection conn = JdbcUtil.getDefaultConnection()) {
            List<String> sqlList = Arrays.asList(
                    "insert into `job` (`job_type`, `job_desc`) values ('111', '111')",
                    "update `job` set `job_desc` = '444' where `job_type` = '111'",
                    "delete from `job` where id > 1",
                    "create table if not exists `job_1` (`id` bigint)",
                    "drop table `job_1`"
            );
            int[] effects = JdbcUtil.executeBatch(conn, sqlList);
            for (int i = 0; i < sqlList.size(); i++) {
                System.out.println("sql: " + sqlList.get(i));
                System.out.println("effect: " + effects[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void executeBatch1() {
        try (Connection conn = JdbcUtil.getDefaultConnection()) {
            String sql = "insert into `job` (`job_type`, `job_desc`) values (?, ?)";
            List<List<Object>> params = Arrays.asList(
                    Arrays.asList("555", "555"),
                    Arrays.asList("666", "666"),
                    Arrays.asList("777", "777); drop table `job`;")
            );
            int[] effects = JdbcUtil.executeBatch(conn, sql, params);
            System.out.println("sql: " + sql);
            for (int i = 0; i < params.size(); i++) {
                System.out.println("params: " + params.get(i));
                System.out.println("effect: " + effects[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取Mysql数据库所有的建表语句
    @Test
    public void executeShowCreateTable() {
        try (Connection conn = JdbcUtil.getDefaultConnection()) {
            String sql = "show tables";
            List<Map<String, String>> tables = JdbcUtil.executeQuery(conn, sql);
            for (Map<String, String> table : tables) {
                sql = String.format("show create table `%s`", table.entrySet().iterator().next().getValue());
                List<Map<String, String>> createTable = JdbcUtil.executeQuery(conn, sql);
                System.out.println(createTable.get(0).get("Create Table"));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void executeQueryByCursor() {
        Properties properties;
        Connection conn = null;
        try {
            properties = FileUtil.parseProperties(new File(
                    FilePathUtil.getAbsolutePath("/properties/application.properties", true)));
            String driver = properties.getProperty("db.driver");
            String url = properties.getProperty("db.url") + "?useCursorFetch=true";
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            conn = JdbcUtil.getConnection(driver, url, user, password);
            String sql = "select * from `constant`";
            List<Map<String, String>> rows = JdbcUtil.executeQueryByCursor(conn, sql, 3);
            System.out.println("sql: " + sql);
            System.out.println("result: " + rows.size());
            rows.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
