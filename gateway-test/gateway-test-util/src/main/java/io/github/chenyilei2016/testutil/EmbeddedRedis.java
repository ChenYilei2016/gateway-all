package io.github.chenyilei2016.testutil;

import redis.embedded.RedisServer;

import java.io.IOException;

/**
 * 内嵌redis
 */
public class EmbeddedRedis {

    public static void startAndFinallyShutdown(Runnable runnable) {
        try {
            RedisServer redisServer = RedisServer.newRedisServer()
//                    .setting("maxheap 200m") // 为啥只在windows下有用..
                    .setting("bind localhost")
                    .setting("timeout 0")
                    .port(6379)
                    .build();

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    if (redisServer.isActive()) {
                        try {
                            redisServer.stop();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });

            try {
                redisServer.start();
                Thread.sleep(500L);
                runnable.run();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                try {
                    redisServer.stop();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
