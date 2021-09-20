package com.public_class.snippets.concurrent;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockinQueueExample
{
    private static final BlockingQueue<Integer> QUEUE = new ArrayBlockingQueue<>(10);

    public static void main(String[] args)
    {
        Thread t1 = new Thread(() -> {
            try
            {
                producer();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try
            {
                consumer();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try
        {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void producer() throws InterruptedException
    {
        Random random = new Random();

        while (true)
        { // thread spinning? or not ;)
            // what is great here, blocking queue will WAIT until queue is ready for action ;)
            QUEUE.put(random.nextInt(100)); // put because it will throw an exception
        }
    }

    public static void consumer() throws InterruptedException
    {
        Random random = new Random();

        while (true)
        {
            Thread.sleep(100);

            if (random.nextInt(10) == 0)
            {
                Integer value = QUEUE.take(); // take because it will throw an exception

                System.out.println("Taken value: " + value + ", queue size: " + QUEUE.size());
            }
        }
    }


}
