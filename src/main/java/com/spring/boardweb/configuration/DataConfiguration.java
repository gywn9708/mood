package com.spring.boardweb.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration 
//해당 클래스를 설정파일로 읽어들이는 어노테이션 
@PropertySource("classpath:/application.properties") 
//설정내용을 어느 파일에서 읽어올 것인지 결정.
//classpath:는 src/main/resource를 의미
@MapperScan(basePackages="com.spring.boardweb.mapper")
//Mapper 클래스를 스캔할 경로 지정
public class DataConfiguration {
	
	@Autowired
	//스프링 컨테이너 = 스프링 설정 파일 등을 읽어와서 사용 가능
	private ApplicationContext applicationContext;
	
	@Bean //Bean객체로 생성해주는 어노테이션 
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	//application.properties 파일 중에서, spring.datasour.hikari로 시작하는 설정들만 읽어오는 설
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSource() throws Exception {
		DataSource dataSource = new HikariDataSource(hikariConfig());
		return dataSource;
	}
	
//Mybatis 연동 설정 시작
	//MyBatis SqlSessionFactory 생성
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/**/*-mapper.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	//MyBatis SqlSessionTemplate 생성
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
//Mybatis 연동 설정 끝 
	
	
	@Bean
	@ConfigurationProperties(prefix="spring.jpa")
	public Properties hibernateConfig() {
		return new Properties();
	}
}
