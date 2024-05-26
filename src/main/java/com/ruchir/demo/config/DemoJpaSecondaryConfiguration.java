package com.ruchir.demo.config;

import com.ruchir.demo.utils.ReadOnlyRepository;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Slf4j
@Profile("!test")
@Configuration
@EnableJpaRepositories(
        basePackages = {"com.ruchir.demo.repository.repo.readonly"},
        includeFilters = @ComponentScan.Filter(ReadOnlyRepository.class),
        entityManagerFactoryRef = "demoSecondaryEntityManagerFactory",
        transactionManagerRef = "demoSecondaryTransactionManager")
public class DemoJpaSecondaryConfiguration {

    @Value("${spring.rdbms.demo.secondary.datasource.url}")
    private String databaseUrl;

    @Value("${spring.rdbms.demo.secondary.datasource.username}")
    private String databaseUserName;

    @Value("${spring.rdbms.demo.secondary.datasource.password}")
    private String databasePassword;

    @Value("${spring.rdbms.demo.secondary.datasource.minimumIdle:1}")
    private String minPoolSize;

    @Value("${spring.rdbms.demo.secondary.datasource.maximumPoolSize:1}")
    private String maxPoolSize;

    @Value("${spring.rdbms.demo.secondary.datasource.idleTimeout:30000}")
    private String idleTimeout;

    @Value("${spring.rdbms.demo.secondary.datasource.poolName}")
    private String poolName;

    @Value("${spring.rdbms.demo.secondary.datasource.maxLifetime:100000}")
    private String maxLifetime;

    @Value("${spring.rdbms.demo.secondary.datasource.connectionTimeout:20000}")
    private String connectionTimeout;

    @Value("${spring.rdbms.demo.secondary.hibernate.show_sql:false}")
    private boolean showSql;

    @Value("${spring.rdbms.demo.secondary.hibernate.default_schema:@null}")
    private String defaultSchema;

    @Value("${spring.rdbms.demo.secondary.hibernate.jdbc_fetch_size:100}")
    private int jdbcFetchSize;

    @Value("${spring.rdbms.demo.secondary.hibernate.jdbc.time_zone:@null}")
    private String jdbcTimeZone;

    @Bean(name = "demoSecondaryDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(databaseUrl)
                .username(databaseUserName)
                .password(databasePassword)
                .build();
    }

    @Bean(name = "demoSecondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(@Qualifier("demoSecondaryDataSource") DataSource dataSource) {
        var entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.ruchir.demo.repository.model");
        entityManagerFactoryBean.setDataSource(dataSource);

        var jpaProperties = new Properties();
        jpaProperties.put("hibernate.hikari.jdbcUrl", databaseUrl);
        jpaProperties.put("hibernate.hikari.username", databaseUserName);
        jpaProperties.put("hibernate.hikari.password", databasePassword);
        jpaProperties.put("hibernate.hikari.minimumIdle", minPoolSize);
        jpaProperties.put("hibernate.hikari.maximumPoolSize", maxPoolSize);
        jpaProperties.put("hibernate.hikari.poolName", poolName);
        jpaProperties.put("hibernate.hikari.maxLifetime", maxLifetime);
        jpaProperties.put("hibernate.hikari.idleTimeout", idleTimeout);
        jpaProperties.put("hibernate.hikari.connectionTimeout", connectionTimeout);
        jpaProperties.put("hibernate.show_sql", showSql);

        if (jdbcTimeZone != null && !"@null".equals(jdbcTimeZone)) {
            jpaProperties.put("hibernate.jdbc.time_zone", jdbcTimeZone);
        }

        if (defaultSchema != null && !"@null".equals(defaultSchema)) {
            jpaProperties.put("hibernate.default_schema", defaultSchema);
        }
        jpaProperties.put("hibernate.jdbc.fetch_size", jdbcFetchSize);
        jpaProperties.put("hibernate.cache.use_second_level_cache", false);
        jpaProperties.put("hibernate.cache.use_query_cache", false);
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        entityManagerFactoryBean.setPersistenceUnitName("demo-Prod-Secondary");

        return entityManagerFactoryBean;
    }

    @Bean(name = "demoSecondaryTransactionManager")
    public JpaTransactionManager secondaryTransactionManager(@Qualifier("demoSecondaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    /**
     * @return JdbcTemplate: JdbcTemplate for getting connection for DB
     */
    @Bean(name = "demoSecondaryJdbcTemplate")
    public JdbcTemplate getJdbcTemplate(@Qualifier("demoSecondaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}