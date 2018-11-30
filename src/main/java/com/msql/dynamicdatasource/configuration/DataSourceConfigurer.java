package com.msql.dynamicdatasource.configuration;

import com.msql.dynamicdatasource.common.DataSourceKey;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class DataSourceConfigurer {


    @Bean("master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }


    @Bean("slaveOne")
    @ConfigurationProperties(prefix = "spring.datasource.slave-one")
    public DataSource slaveOne() {
        return DataSourceBuilder.create().build();
    }


    @Bean("slaveTwo")
    @ConfigurationProperties(prefix = "spring.datasource.slave-two")
    public DataSource slaveTwo() {
        return DataSourceBuilder.create().build();
    }


    @Bean("slaveThree")
    @ConfigurationProperties(prefix = "spring.datasource.slave-three")
    public DataSource slaveThree() {
        return DataSourceBuilder.create().build();
    }


    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        dataSourceMap.put(DataSourceKey.master.name(), master());
        dataSourceMap.put(DataSourceKey.slaveOne.name(), slaveOne());
        dataSourceMap.put(DataSourceKey.slaveTwo.name(), slaveTwo());
        dataSourceMap.put(DataSourceKey.slaveThree.name(), slaveThree());

        dynamicRoutingDataSource.setDefaultTargetDataSource(master());
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());

        DynamicDataSourceContextHolder.slaveDataSourceKeys.addAll(dataSourceMap.keySet());
        DynamicDataSourceContextHolder.slaveDataSourceKeys.remove(DataSourceKey.master.name());
        return dynamicRoutingDataSource;
    }


    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setTypeAliasesPackage("com.msql.dynamicdatasource.modal");
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("mappers/**Mapper.xml"));
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}

