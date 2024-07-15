package io.github.chenyilei2016.gateway.core.datasource.unpooled;

import io.github.chenyilei2016.gateway.core.config.Configuration;
import io.github.chenyilei2016.gateway.core.datasource.Connection;
import io.github.chenyilei2016.gateway.core.datasource.DataSource;
import io.github.chenyilei2016.gateway.core.datasource.DataSourceType;
import io.github.chenyilei2016.gateway.core.datasource.connection.DubboConnection;
import io.github.chenyilei2016.gateway.core.datasource.connection.HTTPConnection;
import io.github.chenyilei2016.gateway.core.mapping.HttpStatement;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author chenyilei
 * @since 2024/07/15 19:45
 */
public class UnpooledDataSource implements DataSource {

    private Configuration configuration;
    private HttpStatement httpStatement;
    private DataSourceType dataSourceType;

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setHttpStatement(HttpStatement httpStatement) {
        this.httpStatement = httpStatement;
    }

    public void setDataSourceType(DataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    @Override
    public Connection getConnection() {
        switch (dataSourceType) {
            case HTTP:
                return new HTTPConnection(httpStatement.getUri());
            case DUBBO:
                // 配置信息
                String application = httpStatement.getApplication();
                String interfaceName = httpStatement.getInterfaceName();
                // 获取服务
                ApplicationConfig applicationConfig = configuration.getApplicationConfig(application);
                RegistryConfig registryConfig = configuration.getRegistryConfig(application);
                ReferenceConfig<GenericService> reference = configuration.getReferenceConfig(interfaceName);
                return new DubboConnection(applicationConfig, registryConfig, reference);
            default:
                break;
        }
        throw new RuntimeException("DataSourceType：" + dataSourceType + "没有对应的数据源实现");
    }
}
