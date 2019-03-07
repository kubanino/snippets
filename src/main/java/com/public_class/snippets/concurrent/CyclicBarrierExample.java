package com.public_class.snippets.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.SECONDS;

// Kubanino @ https://public-class.com/
// https://www.geeksforgeeks.org/java-util-concurrent-cyclicbarrier-java/
public class CyclicBarrierExample
{
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException
    {
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(numberOfThreads);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Task(cyclicBarrier));
        executorService.submit(new Task(cyclicBarrier));
        executorService.submit(new Task(cyclicBarrier));

        cyclicBarrier.await(); // throws two exceptions

        executorService.submit(new Task(cyclicBarrier));
        executorService.submit(new Task(cyclicBarrier));
        executorService.submit(new Task(cyclicBarrier));

        cyclicBarrier.await(); // throws two exceptions
        executorService.shutdown();

        executorService.awaitTermination(5, SECONDS);

    }

    public static class Task implements Runnable
    {
        private final CyclicBarrier barrier;

        public Task(CyclicBarrier barrier)
        {
            this.barrier = barrier;
        }

        @Override
        public void run()
        {
            int timeout = new Random().nextInt(5000);
            System.out.println("Doing something in this thread for " + timeout + "[ms]");
            try
            {
                Thread.sleep(timeout);
                System.out.format("%d waiting, for %d parties %n", barrier.getNumberWaiting(),
                        (barrier.getParties() - barrier.getNumberWaiting()));
                barrier.await();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            catch (BrokenBarrierException e)
            {
                e.printStackTrace(); // might happen i.e when somebody resets the barier
            }
        }
    }
}
