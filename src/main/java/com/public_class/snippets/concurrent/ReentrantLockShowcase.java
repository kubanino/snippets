package com.public_class.snippets.concurrent;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// https://www.youtube.com/watch?v=fjMTaVykOpc&list=PLBB24CFB073F1048E&index=10
// Kubanino @ https://public-class.com/
public class ReentrantLockShowcase
{
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public static void main(String[] args)
    {
        ReentrantLockShowcase reentrantLockShowcase = new ReentrantLockShowcase();

        Thread t1 = new Thread(() -> {
            try
            {
                reentrantLockShowcase.firstThread();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try
            {
                reentrantLockShowcase.secondThread();
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
        reentrantLockShowcase.finished();
    }

    private void increment()
    {
        for (int i = 0; i < 10000; i++)
        {
            count++;
        }
    }

    public void firstThread() throws InterruptedException
    {
        lock.lock(); // start of synchronization barrier
        System.out.println("Locked first thread");

        // lets make him wait
        System.out.println("Waiting");
        condition.await();
        System.out.println("First thread running again");

        try
        {
            System.out.println("Incrementing first");
            increment();
        }
        finally
        {
            System.out.println("Unlocking first thread");
            lock.unlock(); // look, it has to happen
        }
    }

    public void secondThread() throws InterruptedException
    {
        lock.lock(); // start of synchronization barrier
        System.out.println("Locked second thread");

        try
        {
            System.out.println("Incrementing second");
            increment();
            System.out.println("Sending a signal to the first one");
            condition.signal();
        }
        finally
        {
            System.out.println("Unlocking second thread");
            lock.unlock(); // look, it has to happen
        }
    }

    public void finished()
    {
        System.out.println("Count is: " + count);
    }
}
