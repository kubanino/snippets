package com.public_class.snippets.concurrent;

// https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html
public class Drop
{
    private String message;
    private boolean empty = true;

    public synchronized String take()
    {
        while (empty)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        empty = true;

        notifyAll(); // basically send info to whoever holds lock on this
        return message;
    }

    public synchronized void put(String message)
    {
        while (!empty)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }
}