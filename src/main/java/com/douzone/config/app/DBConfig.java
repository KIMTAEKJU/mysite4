package com.douzone.config.app;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // 트랜잭션 매니저
@PropertySource("classpath:com/douzone/config/app/properties/jdbc.properties") // 프로퍼티 여기에있다
public class DBConfig {

	@Autowired
	private Environment env; // 도구
	
	@Bean
	public DataSource basicDatSource()
	{
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName( env.getProperty("jdbc.driverClassName") );
		// spring이니까 &amp를 &로 변경해줘야함 &amp는 xml에서
		basicDataSource.setUrl( env.getProperty("jdbc.url") );
		basicDataSource.setUsername( env.getProperty("jdbc.username") );
		basicDataSource.setPassword( env.getProperty("jdbc.password") );
		basicDataSource.setInitialSize( env.getProperty("jdbc.initialSize", Integer.class) ); // 초기 커넥션개수 
		basicDataSource.setMaxActive( env.getProperty("jdbc.maxActive", Integer.class) ); // 최대 커넥션 개수?
		
		return basicDataSource;
	}
	
	public PlatformTransactionManager transactionManager( DataSource dataSource)
	{
		return new DataSourceTransactionManager(dataSource);
	}
}
