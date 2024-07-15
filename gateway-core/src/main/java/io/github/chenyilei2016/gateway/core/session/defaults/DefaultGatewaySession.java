package io.github.chenyilei2016.gateway.core.session.defaults;

import com.google.common.base.Stopwatch;
import io.github.chenyilei2016.gateway.core.config.Configuration;
import io.github.chenyilei2016.gateway.core.datasource.Connection;
import io.github.chenyilei2016.gateway.core.datasource.DataSource;
import io.github.chenyilei2016.gateway.core.datasource.unpooled.UnpooledDataSourceFactory;
import io.github.chenyilei2016.gateway.core.generic.IGenericReference;
import io.github.chenyilei2016.gateway.core.mapping.HttpStatement;
import io.github.chenyilei2016.gateway.core.session.GatewaySession;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.concurrent.TimeUnit;

public class DefaultGatewaySession implements GatewaySession {

    private Configuration configuration;
    private String uri;
    private DataSource dataSource;

    public DefaultGatewaySession(Configuration configuration, String uri, DataSource dataSource) {
        this.configuration = configuration;
        this.uri = uri;
        this.dataSource = dataSource;
    }

    @Override
    public Object get(String method, Object parameter) {

        Connection connection = dataSource.getConnection();

        return connection.execute(method, new String[]{"java.lang.String"}, new String[]{"name"}, new Object[]{parameter});
    }

    @Override
    public IGenericReference getMapper(String uri) {
        return configuration.getMapper(uri, this);
    }

    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }
}
