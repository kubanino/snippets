package com.public_class.snippets.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

// https://netjs.blogspot.com/2016/08/stampedlock-in-java.html
public class StampedLockExample
{
    int c = 0;

    public static void main(String[] args)
    {
        StampedLock lock = new StampedLock();
        ExecutorService executor = Executors.newFixedThreadPool(3);

        StampedLockExample task = new StampedLockExample();

        Runnable readTask = () -> {
            long stamp = lock.tryOptimisticRead();
            try
            {
                System.out.println("Validating optimistic read - first time: " + lock.validate(stamp));
                System.out.println("Reading value: " + task.getValue());
                try
                {
                    TimeUnit.SECONDS.sleep(2); // cool way of sleeping thread
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("Validating optimistic read - second time: " + lock.validate(stamp));
                System.out.println("Time to do something crazy - convert to write lock and increment!");
                long writeStamp = lock.tryConvertToWriteLock(stamp);
                if (writeStamp != 0L)
                {
                    // you need to compare to zero long iteral
                    System.out.println("I was able to convert to write lock, hurayy");
                    task.increment();
                }
                else {
                    System.out.println("I was not to convert to write lock, shame");
                }
            }
            finally
            {
                lock.unlock(stamp); // notice how you unlock same lock using STAMP
            }
        };

        Runnable writeTask = () -> {
            long stamp = lock.writeLock();
            try
            {
                task.increment();
            }
            finally
            {
                lock.unlock(stamp); // notice how you unlock same lock using STAMP
            }
        };

        // Thanks to stamps, You can use same lock in different modes, convert and so on

        executor.submit(writeTask);
        executor.submit(writeTask);
        executor.submit(writeTask);
        executor.submit(readTask);
        executor.shutdown();
        try
        {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public int getValue()
    {
        return c;
    }

    public void increment()
    {
        c++;
        System.out.println("Incremented value: " + c);
    }
}
