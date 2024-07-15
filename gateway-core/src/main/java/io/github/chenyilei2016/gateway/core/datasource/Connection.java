package io.github.chenyilei2016.gateway.core.datasource;

/**
 * @author chenyilei
 * @since 2024/07/15 19:10
 */
public interface Connection {

    Object execute(String method, String[] parameterTypes, String[] parameterNames, Object[] args);
}
