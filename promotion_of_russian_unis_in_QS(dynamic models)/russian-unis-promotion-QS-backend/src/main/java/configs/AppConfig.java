package configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;
import static org.hibernate.cfg.Environment.*;
@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {
        @ComponentScan("dao"),
        @ComponentScan("services")
})

public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public Properties setJDBCProperties() {
        Properties properties = new Properties();
        properties.put(DRIVER, environment.getProperty("postgres.driver"));
        properties.put(URL, environment.getProperty("postgres.url"));
        properties.put(USER, environment.getProperty("postgres.user"));
        properties.put(PASS, environment.getProperty("postgres.password"));
        return properties;
    }

    @Bean
    public Properties setHibernateProperties() {
        Properties properties = new Properties();
        properties.put(SHOW_SQL, environment.getProperty("hibernate.show_sql"));
        properties.put(DIALECT, environment.getProperty("hibernate.dialect"));
        if (Boolean.parseBoolean(System.getProperty("initial_start"))) {
            properties.put(HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto.create"));
            properties.put(HBM2DDL_IMPORT_FILES, environment.getProperty("hibernate.import_files"));
        } else {
            properties.put(HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto.update"));
        }
        return properties;
    }

    @Bean
    public Properties setC3P0Properties() {
        Properties properties = new Properties();
        properties.put(C3P0_MIN_SIZE, environment.getProperty("hibernate.c3p0.min_size"));
        properties.put(C3P0_MAX_SIZE, environment.getProperty("hibernate.c3p0.max_size"));
        properties.put(C3P0_ACQUIRE_INCREMENT, environment.getProperty("hibernate.c3p0.max_acquire_increment"));
        properties.put(C3P0_TIMEOUT, environment.getProperty("hibernate.c3p0.timeout"));
        properties.put(C3P0_MAX_STATEMENTS, environment.getProperty("hibernate.c3p0.max_statements"));
        return properties;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        Properties properties = new Properties();
        //Setting JDBC properties
        properties.putAll(setJDBCProperties());
        //Setting Hibernate properties
        properties.putAll(setHibernateProperties());
        //Setting C3P0 properties
        properties.putAll(setC3P0Properties());
        factoryBean.setHibernateProperties(properties);
        factoryBean.setPackagesToScan("entities");
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
