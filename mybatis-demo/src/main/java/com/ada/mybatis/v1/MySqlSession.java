package com.ada.mybatis.v1;

/**
 *  会话类，用于获取配置和用执行器执行SQL
 */
public class MySqlSession {
    //配置类
    private MyConfiguration configuration;
    //执行器
    private MyExecutor executor;

    public MySqlSession(MyConfiguration configuration, MyExecutor executor){
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T selectOne(String statementId, Object paramater){
        // 根据statementId拿到SQL
        String sql = MyConfiguration.sqlMappings.getString(statementId);
        if(null != sql && !"".equals(sql)){
            return executor.query(sql, paramater );
        }
        return null;
    }

    /**
     *  通过接口获取动态代理之后的对象
     */
    public <T> T getMapper(Class clazz){
        return configuration.getMapper(clazz, this);
    }

}
