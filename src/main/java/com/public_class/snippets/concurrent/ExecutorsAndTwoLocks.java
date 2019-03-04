package com.public_class.snippets.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

// Kubanino @ https://public-class.com/
public class ExecutorsAndTwoLocks
{
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private final List<Integer> list1 = new ArrayList<>();
    private final List<Integer> list2 = new ArrayList<>();


    public static void main(String[] args)
    {
        ExecutorsAndTwoLocks drop = new ExecutorsAndTwoLocks();
        CountDownLatch latch = new CountDownLatch(1450);

        System.out.println("Ok, so how fast is that?");
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(new Processor(drop, latch));
        Thread t2 = new Thread(new Processor(drop, latch));
        Thread t3 = new Thread(new Processor(drop, latch));

        t1.start();
        t2.start();
        t3.start();

        try
        {
            latch.await();
            t1.interrupt();
            t2.interrupt();
            t3.interrupt();
        }
        catch (InterruptedException e)
        {
            System.out.println("Closing thread: " + Thread.currentThread());
        }

        long end = System.currentTimeMillis();
        System.out.println("It took: " + (end - start));
        System.out.println("Total size of list1 and list2: " + drop.getList1().size() + ", " + drop.getList2().size());
    }

    public List<Integer> getList1()
    {
        return list1;
    }

    public List<Integer> getList2()
    {
        return list2;
    }

    public void incrementFirst(CountDownLatch latch)
    {
        synchronized (lock1)
        {
            for (int i = 0; i < 1000; i++)
            {
                list1.add(0);
                try
                {
                    latch.countDown();
                    Thread.sleep(1);
                }
                catch (InterruptedException e)
                {
                    System.out.println("Closing thread: " + Thread.currentThread());
                }
            }
        }
    }

    public void incrementSecond(CountDownLatch latch)
    {
        synchronized (lock2)
        {
            for (int i = 0; i < 1000; i++)
            {
                list2.add(0);
                try
                {
                    latch.countDown();
                    Thread.sleep(1);
                }
                catch (InterruptedException e)
                {
                    System.out.println("Closing thread: " + Thread.currentThread());
                }
            }
        }
    }

    public static class Processor implements Runnable
    {
        private final ExecutorsAndTwoLocks drop;
        private final CountDownLatch latch;

        public Processor(ExecutorsAndTwoLocks drop, CountDownLatch latch)
        {
            this.drop = drop;
            this.latch = latch;
        }

        @Override
        public void run()
        {
            drop.incrementFirst(latch);
            drop.incrementSecond(latch);
        }
    }
}
