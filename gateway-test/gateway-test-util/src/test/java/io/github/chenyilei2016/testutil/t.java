package io.github.chenyilei2016.testutil;

/**
 * @author chenyilei
 * @since 2024/07/15 11:32
 */
public class t {

    public static void main(String[] args) {

        EmbeddedZooKeeper.startAndFinallyShutdown(() -> {

            try {
                Thread.sleep(2000000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

    }
}
