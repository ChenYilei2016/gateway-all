package io.github.chenyilei2016.gateway.core.session.defaults;

import com.google.common.base.Stopwatch;
import io.github.chenyilei2016.gateway.core.config.Configuration;
import io.github.chenyilei2016.gateway.core.datasource.Connection;
import io.github.chenyilei2016.gateway.core.datasource.DataSource;
import io.github.chenyilei2016.gateway.core.datasource.unpooled.UnpooledDataSourceFactory;
import io.github.chenyilei2016.gateway.core.executor.Executor;
import io.github.chenyilei2016.gateway.core.generic.IGenericReference;
import io.github.chenyilei2016.gateway.core.mapping.HttpStatement;
import io.github.chenyilei2016.gateway.core.session.GatewaySession;
import io.github.chenyilei2016.gateway.core.type.SimpleTypeRegistry;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DefaultGatewaySession implements GatewaySession {
    private static String[] IGNORE_NAMES = new String[0];

    private Configuration configuration;
    private String uri;
    private Executor executor;

    public DefaultGatewaySession(Configuration configuration, String uri, Executor executor) {
        this.configuration = configuration;
        this.uri = uri;
        this.executor = executor;
    }

    /**
     * todo: get post 方法调用
     */
    @Override
    public Object get(String methodName, Map<String, Object> params) {
        HttpStatement httpStatement = configuration.getHttpStatement(uri);
        try {
            return executor.exec(httpStatement, params);
        } catch (Exception e) {
            throw new RuntimeException("Error exec get. Cause: " + e);
        }
    }

    @Override
    public Object post(String methodName, Map<String, Object> params) {
        return get(methodName, params);
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
