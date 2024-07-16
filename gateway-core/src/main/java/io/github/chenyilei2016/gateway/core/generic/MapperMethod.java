package io.github.chenyilei2016.gateway.core.generic;


import io.github.chenyilei2016.gateway.core.config.Configuration;
import io.github.chenyilei2016.gateway.core.mapping.HttpCommandType;
import io.github.chenyilei2016.gateway.core.session.GatewaySession;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @description 绑定调用方法
 */
public class MapperMethod {

    private String methodName;
    private final HttpCommandType command;

    public MapperMethod(String uri, Method method, Configuration configuration) {
        this.methodName = configuration.getHttpStatement(uri).getMethodName();
        this.command = configuration.getHttpStatement(uri).getHttpCommandType();
    }

    public Object execute(GatewaySession session, Map<String, Object> args) {
        Object result = null;
        switch (command) {
            case GET:
                result = session.get(methodName, args);
                break;
            case POST:
                result = session.post(methodName, args);
                break;
            case PUT:
                result = session.post(methodName, args);
                break;
            case DELETE:
                result = session.post(methodName, args);
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + command);
        }
        return result;
    }

}
