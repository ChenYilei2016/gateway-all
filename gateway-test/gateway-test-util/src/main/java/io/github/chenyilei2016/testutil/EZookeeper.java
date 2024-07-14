package io.github.chenyilei2016.testutil;


import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class EZookeeper {
    public static void startAndFinallyShutdown(Runnable runnable) {
//        try {
//            ZooKeeperServerEmbedded.ZookKeeperServerEmbeddedBuilder zookKeeperServerEmbeddedBuilder
//                    = new ZooKeeperServerEmbedded.ZookKeeperServerEmbeddedBuilder();
//            URL resource = EZookeeper.class.getResource("");
//            zookKeeperServerEmbeddedBuilder.baseDir(Paths.get(resource.toURI()));
//
//            Properties props = new Properties();
////            props.setProperty("tickTime", tickTime);
////            props.setProperty("dataDir", dataDir);
//            props.setProperty("clientPort", "2181");
////            props.setProperty("initLimit", initLimit);
////            props.setProperty("syncLimit", syncLimit);
//
//            zookKeeperServerEmbeddedBuilder.configuration(props);
//
//
//            ZooKeeperServerEmbedded e = zookKeeperServerEmbeddedBuilder.build();
//
//            e.start();
//
//            Runtime.getRuntime().addShutdownHook(new Thread() {
//                @Override
//                public void run() {
//                    if (true) {
//                        e.close();
//                    }
//                }
//            });
//
//            try {
//                e.start();
//                Thread.sleep(500L);
//                runnable.run();
//            } catch (Throwable throwable) {
//                throwable.printStackTrace();
//                e.close();
//            }
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//

    }
}
