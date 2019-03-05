package com.public_class.snippets.concurrent;

import java.util.Random;

// https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html
// Kubanino @ https://public-class.com/
public class Producer implements Runnable
{
    private Drop drop;

    public Producer(Drop drop)
    {
        this.drop = drop;
    }

    @Override
    public void run()
    {
        String importantInfos[] = {"Yo yo", "Hi Man", "Wazap Dude", "Greetings",
                "Yo yo", "Hi Man", "Wazap Dude", "Greetings"};
        Random random = new Random();

        for (int i = 0; i < importantInfos.length; i++)
        {
            drop.put(importantInfos[i]);

            try
            {
                Thread.sleep(random.nextInt(500));
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        drop.put("DONE");
    }
}

