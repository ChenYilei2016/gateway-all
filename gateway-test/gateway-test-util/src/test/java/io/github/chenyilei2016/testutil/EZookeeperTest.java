package io.github.chenyilei2016.testutil;

import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class EZookeeperTest {

    @Test
    void startAndFinallyShutdown() {

        EZookeeper.startAndFinallyShutdown(() -> {

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

    }

    @Test
    void path2() {

        URL resource = EZookeeper.class.getResource("");

        System.err.println(resource.getPath());
    }

//    @Test
//    public void t() {
//        try (GenericContainer<?> zookeeper = new GenericContainer<>("zookeeper:3.8.0")
//                .withExposedPorts(2181)
//        ) {
//            zookeeper.start();
//
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//    }
}