package com.public_class.snippets.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.Executors.newFixedThreadPool;

// https://docs.oracle.com/javase/tutorial/essential/concurrency/newlocks.html
// Kubanino @ https://public-class.com/
public class AvoidDeadLockByUsingLockObjects
{
    public static void main(String[] args)
    {
        Friend alicja = new Friend("Alicja");
        Friend jakub = new Friend("Jakub");

        ExecutorService executor = newFixedThreadPool(2);
        executor.submit(new BowLoop(alicja, jakub));
        executor.submit(new BowLoop(jakub, alicja));
    }

    public static class BowLoop implements Runnable
    {
        private final Friend friend;
        private final Friend anotherFriend;

        public BowLoop(Friend friend, Friend anotherFriend)
        {
            this.friend = friend;
            this.anotherFriend = anotherFriend;
        }

        @Override
        public void run()
        {
            Random random = new Random();

            while (true)
            {
                try
                {
                    Thread.sleep(random.nextInt(1));
                }
                catch (InterruptedException e)
                {
                }
                friend.bow(anotherFriend);
            }
        }
    }

    public static class Friend
    {
        private final String name;
        private final Lock lock = new ReentrantLock();

        public Friend(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return name;
        }

        // bow with sanity
        public boolean aquireLocks(Friend bower)
        {
            Boolean myLock = false;
            Boolean yourLock = false;

            try
            {
                myLock = this.lock.tryLock();
                yourLock = bower.lock.tryLock();
            }
            finally
            {
                if (!(myLock && yourLock))
                {
                    if (myLock)
                    {
                        this.lock.unlock();
                    }
                    if (yourLock)
                    {
                        bower.lock.unlock();
                    }
                }
            }
            return myLock && yourLock;
        }

        public void bow(Friend bower)
        {
            if (aquireLocks(bower))
            {
                try
                {
                   // System.out.format("%s: %s bowing with him%n", this.name, bower.name);
                }
                finally
                {
                    lock.unlock();
                    bower.lock.unlock();
                }
            }
            else
            {
                System.out.format("%s: %s wanted to bow, but saw that I am already bowing to him%n",
                        this.name, bower.getName());
            }
        }
    }
}