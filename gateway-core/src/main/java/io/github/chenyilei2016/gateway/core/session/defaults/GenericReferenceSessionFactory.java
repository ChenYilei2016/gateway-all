package io.github.chenyilei2016.gateway.core.session.defaults;

import io.github.chenyilei2016.gateway.core.Configuration;
import io.github.chenyilei2016.gateway.core.session.IGenericReferenceSessionFactory;
import io.github.chenyilei2016.gateway.core.session.SessionServer;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @description 泛化调用会话工厂
 * 
 * 
 */
public class GenericReferenceSessionFactory implements IGenericReferenceSessionFactory {

    private final Logger logger = LoggerFactory.getLogger(GenericReferenceSessionFactory.class);

    private final Configuration configuration;

    public GenericReferenceSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Future<Channel> openSession() throws ExecutionException, InterruptedException {
        SessionServer server = new SessionServer(configuration);

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();

        if (null == channel) throw new RuntimeException("netty server start error channel is null");

        while (!channel.isActive()) {
            logger.info("netty server gateway start Ing ...");
            Thread.sleep(500);
        }
        logger.info("netty server gateway start Done! {}", channel.localAddress());

        return future;
    }

}
