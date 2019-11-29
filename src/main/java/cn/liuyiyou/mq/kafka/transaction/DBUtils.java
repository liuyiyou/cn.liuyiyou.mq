package cn.liuyiyou.mq.kafka.transaction;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: liuyiyou@liuyiyou.cn
 * @date: 2018/10/29
 * @version: V1.0
 * @Copyright: 2018 liuyiyou.cn Inc. All rights reserved.
 */
public class DBUtils {

    JdbcTemplate jdbcTemplate;

    static Connection connection = null;

    public static Connection getConnection() {
        try {
            System.out.println("init");
            connection = DriverManager.getConnection("jdbc:h2:mem:testdb;MODE=MYSQL;DB_CLOSE_DELAY=-1", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static void query() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("select  * from  city");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Object resultSetValue = JdbcUtils.getResultSetValue(resultSet, 1);
                System.out.println(resultSetValue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConnection(connection);
        }
    }

    public static void main(String[] args) {
        DBUtils.query();
    }
}
