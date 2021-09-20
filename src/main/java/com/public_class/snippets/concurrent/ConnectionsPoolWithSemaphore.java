package com.public_class.snippets.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// https://www.youtube.com/watch?v=KxTRsvgqoVQ&list=PLBB24CFB073F1048E&index=12
public class ConnectionsPoolWithSemaphore
{
    public static class Connection
    {
        public static void main(String[] args)
        {
            ExecutorService executor = Executors.newCachedThreadPool();

            for (int i = 0; i < 200; i++)
            {
                executor.submit(Connection.getInstance()::connect);
            }
            executor.shutdown();
            try
            {
                executor.awaitTermination(1, TimeUnit.DAYS);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        private static final Connection instance = new Connection();

        // that true will set the fair order of assigning semaphores
        private Semaphore semaphore = new Semaphore(10, true);

        private int connections = 0;

        private Connection()
        {
        }

        public static Connection getInstance()
        {
            return instance;
        }

        public void connect()
        {
            // lets check if there is an open connection
            try
            {
                semaphore.acquire();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            try
            {
                doConnect();
            }
            finally
            {
                // release this semaphore
                semaphore.release();
            }
        }

        public void doConnect()
        {
            synchronized (this)
            {
                connections++;
                System.out.println("Current connections: " + connections + ", " + Thread.currentThread());
            }
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            synchronized (this)
            {
                System.out.println("Disconnecting: " + Thread.currentThread());
                connections--;
            }
        }
    }
}
