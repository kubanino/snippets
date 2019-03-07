package com.public_class.snippets.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

// Kubanino @ https://public-class.com/
// https://www.baeldung.com/java-phaser
public class PhaserExample
{
    public static void main(String[] args)
    {
        final Phaser phaser = new Phaser(1); // 1 just registers current thread
        ExecutorService executorService = Executors.newCachedThreadPool();

        System.out.println("Registered parties before creating threads: " + phaser.getRegisteredParties());
        executorService.submit(new ControlledTask(phaser, "T1"));
        executorService.submit(new ControlledTask(phaser, "T2"));
        executorService.submit(new ControlledTask(phaser, "T3"));
        System.out.println("Registered parties after creating threads: " + phaser.getRegisteredParties());

        mainWaitsAndArrives(phaser);

        executorService.submit(new ControlledTask(phaser, "T4"));
        executorService.submit(new ControlledTask(phaser, "T5"));

        mainWaitsAndArrives(phaser);
        System.out.println("Nothing to do in next phase...");
        mainWaitsAndArrives(phaser);

        phaser.arriveAndDeregister(); // last deregister, phase is free

        executorService.shutdown();
        try
        {
            executorService.awaitTermination(2, TimeUnit.SECONDS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static class ControlledTask implements Runnable
    {
        private final Phaser phaser;
        private final String threadName;

        public ControlledTask(Phaser phaser, String threadName)
        {
            this.phaser = phaser;
            this.threadName = threadName;
            phaser.register();
        }

        @Override
        public void run()
        {
            System.out.format("Before %s execution, phase is %d %n", threadName, phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            int howLongItWillTake = new Random().nextInt(3000);

            try
            {
                System.out.println(threadName + " starting job in phase: " + phaser.getPhase());
                TimeUnit.MILLISECONDS.sleep(howLongItWillTake);
                System.out.println(threadName + " ending job in phase: " + phaser.getPhase());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            phaser.arriveAndDeregister();
            System.out.format("After %s execution and deregister, phase is %d %n", threadName, phaser.getPhase());
        }
    }

    private static void mainWaitsAndArrives(Phaser phaser)
    {
        System.out.println("Main waits on phase: " + phaser.getPhase() + ", registered parties: " + phaser
                .getRegisteredParties());
        try
        {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        phaser.arriveAndAwaitAdvance();
        System.out.println("Main arrives on phase: " + phaser.getPhase() + ", registered parties: " + phaser
                .getRegisteredParties());
    }
}
