package cn.liuyiyou.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void contextLoads() {
        String sql = "select  * from  city";
        jdbcTemplate.query(sql, resultSet -> {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("city_name"));
            }
        });
    }

}
