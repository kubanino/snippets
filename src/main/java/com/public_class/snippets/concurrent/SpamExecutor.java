package com.public_class.snippets.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Kubanino @ https://public-class.com/
public class SpamExecutor
{
    public static void main(String[] args)
    {
        Runnable task = () -> {
            for (int i = 0; i < 5; i++)
            {
                try
                {
                    System.out.println("Hello from: " + Thread.currentThread());
                    Thread.sleep(800);
                }
                catch (InterruptedException e)
                {
                    System.out.println("They want me to close it: " + Thread.currentThread());
                    break;
                }
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(task);
        executor.submit(task);
        executor.submit(task);
        executor.submit(task);
        try
        {
            executor.shutdown();
            executor.shutdownNow();
            executor.awaitTermination(1, TimeUnit.SECONDS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
