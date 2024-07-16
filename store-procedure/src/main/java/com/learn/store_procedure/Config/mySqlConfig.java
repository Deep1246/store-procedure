package com.learn.store_procedure.Config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.learn.store_procedure.repos.mysqldb",
        transactionManagerRef ="mysqlTransactionMangerFactory",
        entityManagerFactoryRef = "mySqlEntityManagerFactory"

)
@EnableTransactionManagement
public class mySqlConfig {


    @Bean("mySqlDataSourceProperties")
    @ConfigurationProperties("spring.datasource.first")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean("mySqlDataSource")
    @ConfigurationProperties("spring.datasource.first")
    public DataSource dataSource(@Qualifier("mySqlDataSourceProperties") DataSourceProperties dataSourceProperties){
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean("mySqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder ,
                                                                           @Qualifier("mySqlDataSource") DataSource dataSource){
        return builder.dataSource(dataSource)
                .packages("com.learn.store_procedure.model.mysql")
                .persistenceUnit("mysql")
                .build();
    }

    @Bean("mysqlTransactionMangerFactory")
    @ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager transactionManager(
            @Qualifier("mySqlEntityManagerFactory")EntityManagerFactory entityManagerFactory
            ){

        return new JpaTransactionManager(entityManagerFactory);
    }





}
