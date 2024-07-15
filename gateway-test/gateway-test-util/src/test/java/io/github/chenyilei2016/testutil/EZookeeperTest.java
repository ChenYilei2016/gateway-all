package io.github.chenyilei2016.testutil;

import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class EZookeeperTest {

    @Test
    void startAndFinallyShutdown() {

        EmbeddedZooKeeper.startAndFinallyShutdown(() -> {

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

    }

    @Test
    void path2() {

        URL resource = EmbeddedZooKeeper.class.getResource("");

        System.err.println(resource.getPath());
    }


}