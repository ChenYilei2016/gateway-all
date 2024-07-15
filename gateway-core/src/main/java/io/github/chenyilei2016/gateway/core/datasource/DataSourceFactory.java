package io.github.chenyilei2016.gateway.core.datasource;


import io.github.chenyilei2016.gateway.core.config.Configuration;

/**
 * @description 数据源工厂
 */
public interface DataSourceFactory {

    void setProperties(Configuration configuration, String uri);

    DataSource getDataSource();

}
