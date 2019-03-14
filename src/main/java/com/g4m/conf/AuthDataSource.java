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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.g4m.auth.mapper", sqlSessionTemplateRef  = "authSqlSessionTemplate")
public class AuthDataSource {

    @Bean(name = "authData")
    @ConfigurationProperties(prefix = "hikari.primary")
    public DataSource authData() {
//        HikariConfig config = new HikariConfig(getClass().getClassLoader().getResource("db.properties").getPath());
//		HikariConfig config = new HikariConfig();
//		config.setMaximumPoolSize(db_max_conn);
//		config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
//		config.addDataSourceProperty("serverName", "127.0.0.1:5036/auth?characterEncoding=utf8&useSSL=true");
//		config.addDataSourceProperty("port", 5036);
//		config.addDataSourceProperty("databaseName", "auth");
//		config.addDataSourceProperty("user", "root");
//		config.addDataSourceProperty("password", "123456");
//		HikariDataSource dataSource = new HikariDataSource(config);
        return new HikariDataSource();
    }

    @Bean(name = "authSqlSessionFactory")
    public SqlSessionFactory authSqlSessionFactory(@Qualifier("authData") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/g4m/auth/mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "authTransactionManager")
    public DataSourceTransactionManager authTransactionManager(@Qualifier("authData") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "authSqlSessionTemplate")
    public SqlSessionTemplate authSqlSessionTemplate(@Qualifier("authSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
