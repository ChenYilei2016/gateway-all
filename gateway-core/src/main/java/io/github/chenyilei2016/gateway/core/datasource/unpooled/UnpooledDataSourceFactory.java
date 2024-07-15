package io.github.chenyilei2016.gateway.core.datasource.unpooled;


import io.github.chenyilei2016.gateway.core.config.Configuration;
import io.github.chenyilei2016.gateway.core.datasource.DataSource;
import io.github.chenyilei2016.gateway.core.datasource.DataSourceFactory;
import io.github.chenyilei2016.gateway.core.datasource.DataSourceType;

/**
 * 数据源工厂, 可能要生产不同类型的datasource
 * @description
 */
public class UnpooledDataSourceFactory implements DataSourceFactory {

    protected UnpooledDataSource dataSource;

    public UnpooledDataSourceFactory() {
        this.dataSource = new UnpooledDataSource();
    }

    @Override
    public void setProperties(Configuration configuration, String uri) {
        this.dataSource.setConfiguration(configuration);
        this.dataSource.setDataSourceType(DataSourceType.DUBBO);
        this.dataSource.setHttpStatement(configuration.getHttpStatement(uri));
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

}
