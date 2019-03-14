package com.g4m.conf;


import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.g4m.base.mapper", sqlSessionTemplateRef  = "baseSqlSessionTemplate")
public class BaseDataSource {

    @Bean(name = "baseData")
    @ConfigurationProperties(prefix = "hikari.base")
    public DataSource baseData() {
//        HikariConfig config = new HikariConfig(getClass().getClassLoader().getResource("db.properties").getPath());
//		HikariConfig config = new HikariConfig();
//		config.setMaximumPoolSize(db_max_conn);
//		config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
//		config.addDataSourceProperty("serverName", "127.0.0.1:5036/base?characterEncoding=utf8&useSSL=true");
//		config.addDataSourceProperty("port", 5036);
//		config.addDataSourceProperty("databaseName", "base");
//		config.addDataSourceProperty("user", "root");
//		config.addDataSourceProperty("password", "123456");
//		HikariDataSource dataSource = new HikariDataSource(config);
        return new HikariDataSource();
    }

    @Bean(name = "baseSqlSessionFactory")
    public SqlSessionFactory baseSqlSessionFactory(@Qualifier("baseData") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/g4m/base/mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "baseTransactionManager")
    public DataSourceTransactionManager baseTransactionManager(@Qualifier("baseData") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "baseSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate baseSqlSessionTemplate(@Qualifier("baseSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
