package io.github.chenyilei2016.gateway.core.session.defaults;


import io.github.chenyilei2016.gateway.core.config.Configuration;
import io.github.chenyilei2016.gateway.core.datasource.DataSource;
import io.github.chenyilei2016.gateway.core.datasource.unpooled.UnpooledDataSourceFactory;
import io.github.chenyilei2016.gateway.core.executor.Executor;
import io.github.chenyilei2016.gateway.core.session.GatewaySession;
import io.github.chenyilei2016.gateway.core.session.GatewaySessionFactory;

/**
 * @description 默认网关会话工厂
 */
public class DefaultGatewaySessionFactory implements GatewaySessionFactory {

    private final Configuration configuration;

    public DefaultGatewaySessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public GatewaySession openSession(String uri) {
        UnpooledDataSourceFactory unpooledDataSourceFactory = new UnpooledDataSourceFactory();
        unpooledDataSourceFactory.setProperties(configuration, uri);
        DataSource dataSource = unpooledDataSourceFactory.getDataSource();
        // 创建执行器
        Executor executor = configuration.newExecutor(dataSource.getConnection());
        return new DefaultGatewaySession(configuration, uri, executor);
    }

}
