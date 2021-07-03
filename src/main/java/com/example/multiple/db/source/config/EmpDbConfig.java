package com.example.multiple.db.source.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "empEntityManagerFactory", transactionManagerRef = "empTransactionManager", basePackages = {
        "com.example.multiple.db.source.emp.repository" })
public class EmpDbConfig {

    @Bean(name = "empDataSource")
    @ConfigurationProperties(prefix = "spring.emp.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/emp_db")
                .username("root")
                .password("root")
                .build();
    }

    @Bean(name = "empEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean empEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                          @Qualifier("empDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return builder.dataSource(dataSource).properties(properties)
                .packages("com.example.multiple.db.source.emp.entity").persistenceUnit("Employee").build();
    }

    @Bean(name = "empTransactionManager")
    public PlatformTransactionManager empTransactionManager(
            @Qualifier("empEntityManagerFactory") EntityManagerFactory empEntityManagerFactory) {
        return new JpaTransactionManager(empEntityManagerFactory);
    }
}
