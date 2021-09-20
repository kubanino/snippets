package com.public_class.snippets.concurrent;

import java.util.Random;

public class YieldExample
{
    public static void main(String[] args)
    {
        final Object lock = new Object();

        Thread prime = new Thread(new IWillNotGiveUp(lock));
        Thread poorOne = new Thread(new GiveUpAfterFiveSeconds(lock));

        prime.setPriority(10); // max priority
        poorOne.setPriority(1); // min priority

        prime.start();
        poorOne.start();
    }

    public static class IWillNotGiveUp implements Runnable
    {
        private final Object sharedLock;

        public IWillNotGiveUp(Object sharedLock)
        {
            this.sharedLock = sharedLock;
        }

        @Override
        public void run()
        {
            long start = System.currentTimeMillis();
            Random random = new Random();

            synchronized (sharedLock)
            {
                while (true)
                {
                    System.out.println("Im doing something and im prime");
                    try
                    {
                        Thread.sleep(random.nextInt(500));
                        sharedLock.notify();
                        sharedLock.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class GiveUpAfterFiveSeconds implements Runnable
    {
        private final Object sharedLock;

        public GiveUpAfterFiveSeconds(Object sharedLock)
        {
            this.sharedLock = sharedLock;
        }

        @Override
        public void run()
        {
            long start = System.currentTimeMillis();
            Random random = new Random();

            synchronized (sharedLock)
            {
                while (true)
                {
                    System.out.println("Im doing something");
                    try
                    {
                        Thread.sleep(random.nextInt(500));
                        sharedLock.notify();
                        sharedLock.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    if (System.currentTimeMillis() - start > 5000)
                    {
                        break;
                    }
                }
                Thread.yield();
                while (true)
                {
                    System.out.println("Im doing something again!");
                    try
                    {
                        Thread.sleep(random.nextInt(500));
                        sharedLock.notify();
                        sharedLock.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
