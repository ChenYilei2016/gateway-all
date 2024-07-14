package io.github.chenyilei2016.gateway.core.session;

import io.github.chenyilei2016.gateway.core.config.Configuration;
import io.github.chenyilei2016.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import io.github.chenyilei2016.gateway.core.socket.GatewayBootstrapServer;
import io.netty.channel.Channel;
import org.apache.commons.lang3.ThreadUtils;

import java.time.Duration;

class SessionServerTest {

    @org.junit.jupiter.api.Test
    void call() throws Exception {
        System.err.println("test");


        Channel call = new GatewayBootstrapServer(new DefaultGatewaySessionFactory(new Configuration())).call();


        ThreadUtils.sleep(Duration.ofSeconds(1000));
    }
}