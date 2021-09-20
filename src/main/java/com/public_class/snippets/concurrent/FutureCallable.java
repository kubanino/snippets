package com.public_class.snippets.concurrent;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

// https://www.youtube.com/watch?v=KxTRsvgqoVQ&list=PLBB24CFB073F1048E&index=12
public class FutureCallable
{
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> result = executorService.submit(new Task());
        Future<Integer> anotherMagic = executorService.submit(new Task());
        executorService.shutdownNow();

        try
        {
            Integer integer = result.get();
            Integer secondInteger = anotherMagic.get();
            System.out.println("Your lucky number is: " + integer);
            System.out.println("Your second lucky number is: " + secondInteger);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
    }

    public static class Task implements Callable<Integer>
    {
        @Override
        public Integer call() throws IOException
        {
            Random random = new Random();
            int yourLuckyNumber = random.nextInt(5000);
            if (yourLuckyNumber < 3000)
            {
                throw new IOException("No magic Dude");
            }
            System.out.println("Starting the magic");
            try
            {
                Thread.sleep(yourLuckyNumber);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("Ending the magic");
            return yourLuckyNumber;
        }
    }
}
