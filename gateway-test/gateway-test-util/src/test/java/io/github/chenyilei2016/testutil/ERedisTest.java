package io.github.chenyilei2016.testutil;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ERedisTest {

    @Test
    void startAndFinallyShutdown() {

        ERedis.startAndFinallyShutdown(() -> {

            //启动了一个redis
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
    }
}