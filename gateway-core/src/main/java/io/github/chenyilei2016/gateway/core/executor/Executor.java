package io.github.chenyilei2016.gateway.core.executor;


import io.github.chenyilei2016.gateway.core.executor.result.GatewayResult;
import io.github.chenyilei2016.gateway.core.mapping.HttpStatement;

import java.util.Map;

/**
 * @description 执行器
 */
public interface Executor {

    GatewayResult exec(HttpStatement httpStatement, Map<String, Object> params) throws Exception;

}