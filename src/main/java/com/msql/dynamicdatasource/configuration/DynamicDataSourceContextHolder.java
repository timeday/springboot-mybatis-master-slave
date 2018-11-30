package com.msql.dynamicdatasource.configuration;


import com.msql.dynamicdatasource.common.DataSourceKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class DynamicDataSourceContextHolder {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    private static int counter = 0;


    private static final ThreadLocal<String> CONTEXT_HOLDER = ThreadLocal.withInitial(DataSourceKey.master::name);


    //所有的数据key
    public static List<Object> dataSourceKeys = new ArrayList<>();

    //从库数据key
    public static List<Object> slaveDataSourceKeys = new ArrayList<>();


    public static void setDataSourceKey(String key) {
        CONTEXT_HOLDER.set(key);
    }

    //主
    public static void useMasterDataSource() {
        CONTEXT_HOLDER.set(DataSourceKey.master.name());
    }


    public static void useSlaveDataSource() {
        try {
            int datasourceKeyIndex = counter % slaveDataSourceKeys.size();
            CONTEXT_HOLDER.set(String.valueOf(slaveDataSourceKeys.get(datasourceKeyIndex)));
            counter++;
        } catch (Exception e) {
            //容错处理
            useMasterDataSource();
            e.printStackTrace();
        }
    }


    public static String getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }


    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }

    //包含
    public static boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }
}
