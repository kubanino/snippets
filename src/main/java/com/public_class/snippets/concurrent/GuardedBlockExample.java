package com.public_class.snippets.concurrent;

// https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html
public class GuardedBlockExample
{
    public static void main(String[] args)
    {
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }
}
