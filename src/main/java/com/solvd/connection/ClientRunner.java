package com.solvd.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ClientRunner {
    private static final Logger LOGGER = LogManager.getLogger(ClientRunner.class);
    private static ConnectionPool cp = new ConnectionPool(5);

    public static void main(String[] args) throws InterruptedException {

        final int MAX_THREADS = 7;

        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(MAX_THREADS);

        ThreadPoolExecutor executor1 = new ThreadPoolExecutor(MAX_THREADS, MAX_THREADS, 4, TimeUnit.SECONDS, queue);
        for (int i = 0; i < MAX_THREADS; i++) {
            Runnable connection = () -> {
                try {
                    Connection connection1 = cp.getConnection();
                    connection1.connect();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    connection1.disconnect();
                    cp.disconnect(connection1);
                } catch (RuntimeException e) {
                    System.err.println(e.getMessage());
                }
            };
            executor1.execute(connection);
        }
        executor1.shutdown();
        LOGGER.info("Process completed. Finished all threads");
    }
}
