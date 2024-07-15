package io.github.chenyilei2016.gateway.core.datasource;

/**
 * @author chenyilei
 * @description 数据源接口，RPC、HTTP 都当做连接的数据资源使用
 * @since 2024/07/15 19:44
 */
public interface DataSource {

    Connection getConnection();

}
