package by.tiranid.tracker.dao.config;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "by.tiranid.tracker.dao.dao"
)
public class DataConfig {

    private static final String PROPERTY_NAME_DATABASE_DRIVER = ConfigParams.db_driver;
    private static final String PROPERTY_NAME_DATABASE_URL = ConfigParams.db_url;
    private static final String PROPERTY_NAME_DATABASE_USERNAME = ConfigParams.username;
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = ConfigParams.password;

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = ConfigParams.hibernate_dialect;
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = ConfigParams.show_sql;
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = ConfigParams.entitymanager_packages;
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = ConfigParams.hbm2ddl_auto;



    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        //entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
        entityManagerFactoryBean.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaProperties(hibernateProp());

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
        dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
        dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
        dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);
        return dataSource;
    }

    private Properties hibernateProp() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", PROPERTY_NAME_HIBERNATE_DIALECT);
        properties.put("hibernate.show_sql", PROPERTY_NAME_HIBERNATE_SHOW_SQL);
        properties.put("hibernate.hbm2ddl.auto", PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO);
        return properties;
    }

}
