package com.cfysu.lab.spring.jdbc;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.sql.DataSource;

import lombok.Data;
import org.junit.Test;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * @Author canglong
 * @Date 2023/2/24
 */
public class JDBCTest {

    @Test
    public void testDataSource() {
        //"jdbc:mysql://127.0.0.1/ssm", "root", "root"
        DataSource dataSource = DataSourceBuilder.create().url("jdbc:mysql://127.0.0.1/daily_db").username("root").type(
            com.zaxxer.hikari.HikariDataSource.class).build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM daily_db.apaas_app_robot_plugins");

        Stream<RobotPlugin> robotPluginStream = jdbcTemplate.queryForStream(
            "SELECT * FROM daily_db.apaas_app_robot_plugins",
            new BeanPropertyRowMapper<>(RobotPlugin.class));
        robotPluginStream.forEach(System.out::println);
    }

    @Data
    public static class RobotPlugin {
        private Integer id;
        private String pluginCode;
    }
}
