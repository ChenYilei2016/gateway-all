package io.github.chenyilei2016.gateway.core.session;

import io.github.chenyilei2016.gateway.core.config.Configuration;
import io.github.chenyilei2016.gateway.core.mapping.HttpCommandType;
import io.github.chenyilei2016.gateway.core.mapping.HttpStatement;
import io.github.chenyilei2016.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import io.github.chenyilei2016.gateway.core.socket.GatewayBootstrapServer;
import io.netty.channel.Channel;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author chenyilei
 * @since 2024/07/15 16:34
 */
public class ServerTest {

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        // 1. 创建配置信息加载注册
        Configuration configuration = new Configuration();
        HttpStatement httpStatement = new HttpStatement(
                "api-gateway-test", //应用
                "io.github.chenyilei2016.providerApi.IActivityBooth", //接口
                "sayHi", //方法
                "/wg/activity/sayHi",
                HttpCommandType.GET);
        configuration.addMapper(httpStatement);

        // 2. 基于配置构建会话工厂
        DefaultGatewaySessionFactory gatewaySessionFactory = new DefaultGatewaySessionFactory(configuration);

        // 3. 创建启动网关网络服务
        GatewayBootstrapServer server = new GatewayBootstrapServer(gatewaySessionFactory);
        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();
        if (null == channel) throw new RuntimeException("netty server start error channel is null");
        while (!channel.isActive()) {
            System.err.println("netty server gateway start Ing ...");
            Thread.sleep(500);
        }
        System.err.println("netty server gateway start Done! " + channel.localAddress());

        Thread.sleep(Long.MAX_VALUE);
    }


    /**
     * 测试调用
     * curl http://localhost:7397/wg/activity/sayHi
     */
}
