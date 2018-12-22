package com.ebaotech.o2o.dao.split;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicDataSourceHolder {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);
    private static ThreadLocal<String> contextHolde = new ThreadLocal<String>();
    public static final String DB_MASTER = "master";
    public static final String DB_SLAVE = "slave";

    public static String getDBType(){
        String db = contextHolde.get();
        if(db == null){
            db = DB_MASTER;
        }
        return db;
    }

    public static void setDBType(String str){
        logger.debug("所使用的数据源为：" + str);
        contextHolde.set(str);
    }

    public static void clearDBType(){
        contextHolde.remove();
    }

}
