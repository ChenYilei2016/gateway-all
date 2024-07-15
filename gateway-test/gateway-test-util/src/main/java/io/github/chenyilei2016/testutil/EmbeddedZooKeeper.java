package io.github.chenyilei2016.testutil;

import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.admin.AdminServer;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

/**
 * @author chenyilei
 * @since 2024/07/15 11:19
 */
public class EmbeddedZooKeeper {

    public static void startAndFinallyShutdown(Runnable runnable) {
        startAndFinallyShutdown(runnable, null);
    }

    public static void startAndFinallyShutdown(Runnable runnable, Integer iport) {
        try {

            Thread thread = new Thread(() -> {

                try {
                    int port = iport == null ? 2181 : iport;

                    Properties properties = new Properties();
                    File file = new File(System.getProperty("java.io.tmpdir")
                            + File.separator + UUID.randomUUID());
                    file.deleteOnExit();
                    System.err.println(System.getProperty("java.io.tmpdir"));
                    System.err.println("file path: " + file.getAbsolutePath());
                    properties.setProperty("dataDir", file.getAbsolutePath());
                    properties.setProperty("clientPort", String.valueOf(port));

                    QuorumPeerConfig quorumPeerConfig = new QuorumPeerConfig();
                    quorumPeerConfig.parseProperties(properties);

                    ZooKeeperServerMain zkServer = new ZooKeeperServerMain();
                    ServerConfig configuration = new ServerConfig();
                    configuration.readFrom(quorumPeerConfig);

                    System.setProperty("zookeeper.admin.enableServer", "false");
                    System.err.println("zookeeper server will started !!!");
                    zkServer.runFromConfig(configuration);

                    Runtime.getRuntime().addShutdownHook(new Thread() {
                        final ZooKeeperServerMain tmpServer = zkServer;

                        @Override
                        public void run() {
                            tmpServer.close();
                        }
                    });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            thread.setDaemon(true);
            thread.start();

            Thread.sleep(1000);

            runnable.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
