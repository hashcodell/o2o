package com.ebaotech.o2o.dao.split;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import sun.plugin2.main.server.ResultHandler;

import java.util.Locale;
import java.util.Properties;
/*
 * 对于实现自己的Interceptor而言有两个很重要的注解，
 * 一个是@Intercepts，其值是一个@Signature数组。@Intercepts用于表明当前的对象是一个Interceptor，
 * 而@Signature则表明要拦截的接口、方法以及对应的参数类型。
 */
//添加签名，指定拦截那些类型。update会封装增加、删除、修改操作，所以不会再写insert和delete
//query会返回结果，需要RowBounds.class和ResultHandler.class处理
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class }) })
public class DynamicDataSourceInterceptor implements Interceptor {
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceInterceptor.class);
    //正则表达insert或delete或update开头,\\u0020表示空格
    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //在Service层中，指定事务注解@Transactional,如果拦截器拦截到带有事务的方法，则synchronizationActive为true。事务操作要写主库
        boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
        Object[] objects = invocation.getArgs();
        //获取mybatis的mapper.xml中要执行的sql的第一个参数，在此为insert
				/*<insert id="insertShop" useGeneratedKeys="true" keyColumn="shop_id"
							sql语句
				  </insert>*/
        MappedStatement ms = (MappedStatement) objects[0];
        String lookupKey = DynamicDataSourceHolder.DB_MASTER;
        //如果为事务操作，要写库，否则先判断是不是select读操作。
        if (synchronizationActive != true) {
            // 如果为select操作
            if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                // selectKey 为自增id查询主键(SELECT LAST_INSERT_ID())方法，使用主库
                if (ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                    lookupKey = DynamicDataSourceHolder.DB_MASTER;
                } else {
                    //如果不是select操作，要判断是否是insert,delete,update等操作，如果是也要使用主库。
                    //第二个参数就是sql语句
                    BoundSql boundSql = ms.getSqlSource().getBoundSql(objects[1]);
                    //将所有制表符、回车、换行符都替换成空格。
                    String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
                    if (sql.matches(REGEX)) {
                        //如果是insert、delete、update使用主库
                        lookupKey = DynamicDataSourceHolder.DB_MASTER;
                    } else {
                        //如果是读使用从库
                        lookupKey = DynamicDataSourceHolder.DB_SLAVE;
                    }
                }
            }
        } else {
            lookupKey = DynamicDataSourceHolder.DB_MASTER;
        }
        logger.debug("设置方法[{}] use [{}] Strategy, SqlCommanType [{}]..", ms.getId(), lookupKey,
                ms.getSqlCommandType().name());
        DynamicDataSourceHolder.setDBType(lookupKey);
        return invocation.proceed();
    }
    //当拦截的对象是Executor(org.apache.ibatis.executor.Executor),则拦截器将intercept()方法包装进去。如果不是，则直接返回本体。
    //Executor是包含CRUD的操作
    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties arg0) {
        // TODO Auto-generated method stub

    }

}

