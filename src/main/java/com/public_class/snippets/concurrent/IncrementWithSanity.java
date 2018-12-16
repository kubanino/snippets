package com.public_class.snippets.concurrent;

import static java.lang.Thread.currentThread;

// Kubanino @ https://public-class.com/
public class IncrementWithSanity
{
    private static int VALUE = 0;

    private static Runnable INCREMENT_LIKE_A_CRAZY = () ->
    {
        for (int i = 0; i < 500_000; i++)
        {
            VALUE++;
        }
    };

    private static Runnable INCREMENT_LIKE_A_PRO = () ->
    {
        for (int i = 0; i < 500_000; i++)
        {
            synchronized (IncrementWithSanity.class)
            {
                ++VALUE;
            }
        }
    };

    private static void incrementWithMultipleThreads(final Runnable incrementMethod)
    {
        Thread threadOne = new Thread(incrementMethod);
        Thread threadTwo = new Thread(incrementMethod);

        threadOne.start();
        threadTwo.start();

        try
        {
            threadOne.join();
            threadTwo.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("All threads are now finished. We should expect value 1_000_000");

        READ_VALUE.run();
    }

    private static Runnable READ_VALUE = () ->
    {
        synchronized (currentThread())
        {
            System.out.println("Value equals to: " + VALUE);
        }
    };

    public static void main(String[] args)
    {
        incrementWithMultipleThreads(INCREMENT_LIKE_A_CRAZY);

        System.out.println("Why it is different? Because increment operation is NOT atomic!");
        System.out.println("Let's reset value and try with different approach...");

        VALUE = 0;

        incrementWithMultipleThreads(INCREMENT_LIKE_A_PRO);
    }
}
