package com.public_class.snippets.concurrent;

import java.util.Scanner;

// https://www.youtube.com/watch?v=gx_YUORX5vk&list=PLBB24CFB073F1048E&index=8
// Kubanino @ https://public-class.com/
public class WaitNotify
{
    public static void main(String[] args)
    {
        final Processor processor = new Processor();

        Thread producerThread = new Thread(new Producer(processor));
        Thread consumerThread = new Thread(new Consumer(processor));

        producerThread.start();
        consumerThread.start();
    }

    public static class Consumer implements Runnable
    {
        private final Processor processor;

        public Consumer(Processor processor)
        {
            this.processor = processor;
        }

        @Override
        public void run()
        {
            try
            {
                processor.consume();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private static class Producer implements Runnable
    {
        private final Processor processor;

        private Producer(Processor processor)
        {
            this.processor = processor;
        }

        @Override
        public void run()
        {
            try
            {
                processor.produce();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static class Processor
    {
        public void produce() throws InterruptedException
        {
            // synchronized, because somehow You need to obtain Mutex, Dude
            synchronized (this)
            {
                System.out.println("I'm producing something");
                wait();
                System.out.println("I have just regained the controll");
            }
        }

        public void consume() throws InterruptedException
        {
            Scanner scanner = new Scanner(System.in);

            synchronized (this)
            {
                System.out.println("Starting consumer job, let me think...");
                Thread.sleep(1000);
                System.out.println("I will work only after You press the enter key");
                scanner.nextLine();
                notify();
                System.out.println("You would expect, that after notify producer will start again ... NO! " +
                        "Im still the owner of Mutex");
                Thread.sleep(2000);
            }
        }
    }
}
