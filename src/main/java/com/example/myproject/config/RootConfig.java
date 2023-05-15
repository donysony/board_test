package com.example.myproject.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
    //base-package속성 : 지정된 패키지의 모든 Mybatis관련 어노테이션을 찾아 처리 @MapperScan : Mybatis-spring
@MapperScan(basePackages = {"com.example.myproject.mapper"})
@ComponentScan(basePackages = "com.example.myproject.service")
@ComponentScan(basePackages = "com.example.myproject.controller")
public class RootConfig {
    @Bean
    public DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springboot_test?useUnicode=true&characterEncoding=utf8");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;

    }

    //Mybatis에서 핵심객체 SQLSession: Connection을 생성 or SQL을 전달, 결과 리턴받는 구조로 작성, SQLSessionFactory:내부적으로 SQLSession을 생성
    //스프링의 SqlSessionFactory를 등록하는 작업 SqlSessionFactoryBean이용, 스프링과 연동작업 처리하는 mybatis-spring라이브러리에 존재
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }

    //thymeleaf layout
    @Bean
    public LayoutDialect layoutDialect(){
        return new LayoutDialect();
    }




}
