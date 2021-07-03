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
@EnableJpaRepositories(entityManagerFactoryRef = "usrEntityManagerFactory", transactionManagerRef = "usrTransactionManager", basePackages = {
        "com.example.multiple.db.source.usr.repository" })
public class UserDbConfig {

    @Bean(name = "usrDataSource")
    @ConfigurationProperties(prefix = "spring.usr.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/usr_db")
                .username("root")
                .password("root")
                .build();
    }

    @Bean(name = "usrEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean usrEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                          @Qualifier("usrDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return builder.dataSource(dataSource).properties(properties)
                .packages("com.example.multiple.db.source.usr.entity").persistenceUnit("User").build();
    }

    @Bean(name = "usrTransactionManager")
    public PlatformTransactionManager usrTransactionManager(
            @Qualifier("usrEntityManagerFactory") EntityManagerFactory usrEntityManagerFactory) {
        return new JpaTransactionManager(usrEntityManagerFactory);
    }
}
