package com.edupay.authentication.configuration;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.edupay.authentication.configuration"})
@PropertySource(value = { "classpath:application.properties" })
public class EduPayHibernateConfiguration {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean localSessionFactoryBean =  new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(dataSource());
		localSessionFactoryBean.setPackagesToScan(new String[] {
				"com.edupay.authentication.data.model"
		});
		localSessionFactoryBean.setHibernateProperties(hibernateProperties());
		
		return localSessionFactoryBean;
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(environment.getRequiredProperty("com.mysql.jdbc.Driver"));
		driverManagerDataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		driverManagerDataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		driverManagerDataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		return driverManagerDataSource;
		
	}
	
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		return properties;
	}
	
	@Autowired
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory);
		return hibernateTransactionManager;
		
	}

}
