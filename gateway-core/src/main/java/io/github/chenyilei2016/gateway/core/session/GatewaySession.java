package io.github.chenyilei2016.gateway.core.session;


import io.github.chenyilei2016.gateway.core.config.Configuration;
import io.github.chenyilei2016.gateway.core.generic.IGenericReference;

/**
 * @description 用户处理网关 HTTP 请求
 */
public interface GatewaySession {

    Object get(String method, Object parameter);

    IGenericReference getMapper(String uri);

    Configuration getConfiguration();

}