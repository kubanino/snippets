package com.public_class.snippets.concurrent;

// Kubanino @ https://public-class.com/
public class Interruptions
{

    public static void main(String[] args) throws InterruptedException
    {
        Thread taskThread = new Thread(giveMeCoolTask());
        taskThread.start();
        Thread.sleep(3_000); // after 3 seconds ...
        taskThread.interrupt(); // interrupt him
        taskThread.join(1000); // give him 1000 seconds to finish
    }

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
