package kr.board.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages= {"kr.board.mapper"})
@PropertySource({"classpath:persistence-mysql.properties"})
public class RootConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource myDataSource() {
		System.out.println("++++++++++env : "+env.getProperty("jdbc.driver"));
		HikariConfig hikariConfig = new HikariConfig();
		System.out.println("1");
		hikariConfig.setDriverClassName(env.getProperty("jdbc.driver"));
		System.out.println("2");

		hikariConfig.setJdbcUrl(env.getProperty("jdbc.url"));
		System.out.println("3");

		hikariConfig.setUsername(env.getProperty("jdbc.user"));
		System.out.println("4");

		hikariConfig.setPassword(env.getProperty("jdbc.password"));
		System.out.println("5");

		
		HikariDataSource myDataSource = new HikariDataSource(hikariConfig);
		System.out.println("6");

		return myDataSource;
		
	}
	
	@Bean
	public SqlSessionFactory sessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		
		sessionFactory.setDataSource(myDataSource());
		
		return (SqlSessionFactory) sessionFactory.getObject();
		
	}
	

}
