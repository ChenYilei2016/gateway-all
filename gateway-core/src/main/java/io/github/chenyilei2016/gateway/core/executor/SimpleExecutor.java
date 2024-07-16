package io.github.chenyilei2016.gateway.core.executor;


import io.github.chenyilei2016.gateway.core.config.Configuration;
import io.github.chenyilei2016.gateway.core.datasource.Connection;

/**
 * @description 简单执行器
 */
public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration, Connection connection) {
        super(configuration, connection);
    }

    @Override
    protected Object doExec(String methodName, String[] parameterTypes, Object[] args) {
        /*
         * FIXME: 暂时不支持 多参数
         * 01(允许)：java.lang.String
         * 02(允许)：io.github.chenyilei2016.XReq
         */
        return connection.execute(methodName, parameterTypes, new String[]{"ignore"}, args);
    }

}
