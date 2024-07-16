package io.github.chenyilei2016.gateway.core.session.defaults;

import com.google.common.base.Stopwatch;
import io.github.chenyilei2016.gateway.core.config.Configuration;
import io.github.chenyilei2016.gateway.core.datasource.Connection;
import io.github.chenyilei2016.gateway.core.datasource.DataSource;
import io.github.chenyilei2016.gateway.core.datasource.unpooled.UnpooledDataSourceFactory;
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
    private DataSource dataSource;

    public DefaultGatewaySession(Configuration configuration, String uri, DataSource dataSource) {
        this.configuration = configuration;
        this.uri = uri;
        this.dataSource = dataSource;
    }

    /**
     * todo: get post 方法调用
     */
    @Override
    public Object get(String methodName, Map<String, Object> params) {
        Connection connection = dataSource.getConnection();
        HttpStatement httpStatement = configuration.getHttpStatement(uri);
        String parameterType = httpStatement.getParameterType();

        /*
         * FIXME: 暂时不支持 多参数
         * 01(允许)：java.lang.String
         * 02(允许)：io.github.chenyilei2016.XReq
         *
         */
        Object[] parameters = SimpleTypeRegistry.isSimpleType(parameterType) ? params.values().toArray() : new Object[]{params};

        if (parameters.length == 0) {
            parameters = new Object[1];
        }

        return connection.execute(methodName,
                new String[]{parameterType},
                IGNORE_NAMES,
                parameters
        );
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
