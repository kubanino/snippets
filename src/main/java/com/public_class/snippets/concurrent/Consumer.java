package com.public_class.snippets.concurrent;

import java.util.Random;

// https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html
public class Consumer implements Runnable
{
    private Drop drop;

    public Consumer(Drop drop)
    {
        this.drop = drop;
    }

    @Override
    public void run()
    {
        Random random = new Random();

        for (String message = drop.take(); !message.equals("DONE"); message = drop.take())
        {
            System.out.println(Thread.currentThread() + "MESSAGE: " + message);
            try
            {
                Thread.sleep(random.nextInt(5000));
            }
            catch (InterruptedException e)
            {
            }
        }
    }
}