package io.github.chenyilei2016.testMain;

import io.github.chenyilei2016.testutil.EmbeddedZooKeeper;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Configurable
@EnableDubbo
public class ApiGatewayTestApplication {

    public static void main(String[] args) {
        StaticLoggerBinder.getSingleton().getLoggerFactory();
        EmbeddedZooKeeper.startAndFinallyShutdown(() -> {
            SpringApplication.run(ApiGatewayTestApplication.class, args);
        });
    }

}
