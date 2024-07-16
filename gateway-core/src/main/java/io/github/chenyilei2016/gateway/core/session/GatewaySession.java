package io.github.chenyilei2016.gateway.core.session;


import io.github.chenyilei2016.gateway.core.config.Configuration;
import io.github.chenyilei2016.gateway.core.generic.IGenericReference;

import java.util.Map;

/**
 * @description 用户处理网关 HTTP 请求
 */
public interface GatewaySession {

    Object get(String methodName, Map<String, Object> params);

    Object post(String methodName, Map<String, Object> params);

    IGenericReference getMapper(String uri);

    Configuration getConfiguration();

}