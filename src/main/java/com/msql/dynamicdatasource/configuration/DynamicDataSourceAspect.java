package com.msql.dynamicdatasource.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DynamicDataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    private final String[] QUERY_PREFIX = {"select","find","get"};


    @Pointcut("execution( * com.msql.dynamicdatasource.mapper.*.*(..))")
    public void daoAspect() {
    }
    //设置数据源
    @Before("daoAspect()")
    public void switchDataSource(JoinPoint point) {
        Boolean isQueryMethod = isQueryMethod(point.getSignature().getName());
        if (isQueryMethod) {
            DynamicDataSourceContextHolder.useSlaveDataSource();
        }
        System.out.println("生效的数据源"+DynamicDataSourceContextHolder.getDataSourceKey());
    }

    //清楚数据源
    @After("daoAspect())")
    public void restoreDataSource(JoinPoint point) {
        System.out.println("需要清除的数据源"+DynamicDataSourceContextHolder.getDataSourceKey());
        DynamicDataSourceContextHolder.clearDataSourceKey();
        logger.debug("Restore DataSource to [{}] in Method [{}]",
                DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
    }

        //  mapper中的方法名匹配
    private Boolean isQueryMethod(String methodName) {
        for (String prefix : QUERY_PREFIX) {
            if (methodName.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

}
