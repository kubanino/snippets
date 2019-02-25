package com.public_class.snippets.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// Kubanino @ https://public-class.com/
public class InterruptionsWithExecutors
{

    public static void main(String[] args) throws InterruptedException
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> futureTask = executor.submit(giveMeCoolTask()); // You can kill the futureTask
        Thread.sleep(3000);
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        // how to kill just this task?

    }

    @SuppressWarnings("Duplicates")
    private static Runnable giveMeCoolTask()
    {
        return () -> {
            for (int i = 0; i < 10; i++)
            {
                System.out.println(i);
                try
                {
                    Thread.sleep(1_000);
                }
                catch (InterruptedException e)
                {
                    break;
                }
            }
        };
    }
}
