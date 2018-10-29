package cn.liuyiyou.mq.kafka.transaction;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: liuyiyou@yanglaoban.com
 * @date: 2018/10/29
 * @version: V1.0
 * @Copyright: 2018 yanglaoban.com Inc. All rights reserved.
 */
public class DBUtils {

    JdbcTemplate jdbcTemplate;

    static Connection connection = null;

    public static Connection getConnection() {
        try {
            System.out.println("init");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true", "root", "123456");
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
            preparedStatement = connection.prepareStatement("select  * from  t_test");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
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
