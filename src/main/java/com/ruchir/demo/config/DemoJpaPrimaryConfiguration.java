package com.ruchir.demo.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Slf4j
@Profile("!test")
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableJpaRepositories(
        basePackages = {"com.ruchir.demo.repository.repo"},
        includeFilters = @ComponentScan.Filter(Repository.class),
        entityManagerFactoryRef = "demoPrimaryEntityManagerFactory",
        transactionManagerRef = "demoPrimaryTransactionManager"
)
public class DemoJpaPrimaryConfiguration {

    @Value("${spring.rdbms.demo.primary.datasource.url}")
    private String databaseUrl;

    @Value("${spring.rdbms.demo.primary.datasource.username}")
    private String databaseUserName;

    @Value("${spring.rdbms.demo.primary.datasource.password}")
    private String databasePassword;

    @Value("${spring.rdbms.demo.primary.datasource.minimumIdle:1}")
    private String minPoolSize;

    @Value("${spring.rdbms.demo.primary.datasource.maximumPoolSize:1}")
    private String maxPoolSize;

    @Value("${spring.rdbms.demo.primary.datasource.idleTimeout:30000}")
    private String idleTimeout;

    @Value("${spring.rdbms.demo.primary.datasource.poolName}")
    private String poolName;

    @Value("${spring.rdbms.demo.primary.datasource.maxLifetime:100000}")
    private String maxLifetime;

    @Value("${spring.rdbms.demo.primary.datasource.connectionTimeout:20000}")
    private String connectionTimeout;

    @Value("${spring.rdbms.demo.primary.hibernate.show_sql:false}")
    private boolean showSql;

    @Value("${spring.rdbms.demo.primary.hibernate.default_schema:@null}")
    private String defaultSchema;

    @Value("${spring.rdbms.demo.primary.hibernate.ddl-auto:validate}")
    private String ddlAuto;

    @Value("${spring.rdbms.demo.primary.hibernate.jdbc_fetch_size:100}")
    private int jdbcFetchSize;

    @Value("${spring.rdbms.demo.primary.hibernate.use_second_level_cache:false}")
    private boolean useSecondLevelCache;

    @Value("${spring.rdbms.demo.primary.hibernate.use_query_cache:false}")
    private boolean useQueryLevelCache;

    @Value("${spring.rdbms.demo.primary.hibernate.generate_statistics:false}")
    private boolean generateStatistics;

    @Value("${spring.rdbms.demo.primary.hibernate.jdbc.batch_size:300}")
    private int batchSize;

    @Value("${spring.rdbms.demo.primary.hibernate.jdbc.order_inserts:true}")
    private boolean orderInserts;

    @Value("${spring.rdbms.demo.primary.hibernate.jdbc.time_zone:@null}")
    private String jdbcTimeZone;

    @Bean(name = "demoDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(databaseUrl)
                .username(databaseUserName)
                .password(databasePassword)
                .build();
    }

    @Bean(name = "demoPrimaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(@Qualifier("demoDataSource") DataSource dataSource) {
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

        //Hibernate general config application-local.yml
        jpaProperties.put("hibernate.show_sql", showSql);

        //For Batch Inserts saveAll() methods
        jpaProperties.put("hibernate.generate_statistics", generateStatistics);
        jpaProperties.put("hibernate.jdbc.batch_size", batchSize);
        jpaProperties.put("hibernate.order_inserts", orderInserts);

        if (jdbcTimeZone != null && !"@null".equals(jdbcTimeZone)) {
            jpaProperties.put("hibernate.jdbc.time_zone", jdbcTimeZone);
        }

        if (defaultSchema != null && !"@null".equals(defaultSchema)) {
            jpaProperties.put("hibernate.default_schema", defaultSchema);
        }

        if (ddlAuto != null && !"@null".equals(ddlAuto)) {
            jpaProperties.put("hibernate.hbm2ddl.auto", ddlAuto);
        }

        jpaProperties.put("hibernate.jdbc.fetch_size", jdbcFetchSize);

        //Caching Config
        jpaProperties.put("hibernate.cache.use_second_level_cache", useSecondLevelCache);
        jpaProperties.put("hibernate.cache.use_query_cache", useQueryLevelCache);

        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        entityManagerFactoryBean.setPersistenceUnitName("demo-Prod-Primary");
        return entityManagerFactoryBean;
    }

    @Bean(name = "demoPrimaryTransactionManager")
    public JpaTransactionManager primaryTransactionManager(@Qualifier("demoPrimaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    /**
     * @return JdbcTemplate: JdbcTemplate for getting connection for DB
     */
    @Bean(name = "demoPrimaryJdbcTemplate")
    public JdbcTemplate getJdbcTemplate(@Qualifier("demoDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}