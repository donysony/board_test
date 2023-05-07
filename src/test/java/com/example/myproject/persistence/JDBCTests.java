package com.example.myproject.persistence;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.assertj.core.api.Fail.fail;

@Log4j
public class JDBCTests {
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testConnection() throws Exception{
        Connection con =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/springboot_test?serverTimezone=UTC&characterEncoding=UTF-8",
                        "root",
                        "root"
                );
        try(con;){
            log.info(con);
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
}
