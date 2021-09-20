package com.public_class.snippets.concurrent;

public class JoinThreads
{
    public static void main(String[] args) throws InterruptedException
    {
        Thread taskThread = new Thread(() -> {
            for (int i = 0; i < 10; i++)
            {
                System.out.println(i);
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    break;
                }
            }
        });

        taskThread.start();

        taskThread.join(2000);
        System.out.println("Sorry dude, have no time to wait more...");
        taskThread.interrupt();
    }
}
