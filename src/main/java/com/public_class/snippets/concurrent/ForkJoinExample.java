package com.public_class.snippets.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

// Kubanino @ https://public-class.com/
public class ForkJoinExample
{
    public static void main(String[] args)
    {
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors);

        int size = 128;
        int[] inputArray = new int[size];
        int[] outputArray = new int[size];

        // initialize input
        for (int i = 0; i < size; i++)
        {
            inputArray[i] = i;
        }

        // initialize the first task
        ForkAddFive forkAddFive = new ForkAddFive(inputArray, 0, size - 1, outputArray);

        // initialize the pool, note - by default it will get no of processors, but we do that explicit
        ForkJoinPool pool = new ForkJoinPool(processors);

        // invoke it
        pool.invoke(forkAddFive);

        for (int i = 0; i < size; i++)
        {
            System.out.print(outputArray[i] + " ");
        }
    }

    // Write a RecursiveTask (can return value) or RecursiveAction(void returned)
    public static class ForkAddFive extends RecursiveAction
    {
        private int[] sourceArray;
        private int start;
        private int end;
        private int[] output;

        public ForkAddFive(int[] sourceArray, int start, int end, int[] output)
        {
            this.sourceArray = sourceArray;
            this.start = start;
            this.end = end;
            this.output = output;
        }

        @Override
        protected void compute()
        {
            // problem is already small
            if ((end - start) < 10)
            {
                System.out.format("Problem is already small, invoking directly: <%s,%s>%n", start, end);
                computeWithoutSlicing();
            }
            // slice, because the problem is big, slice into two parts
            else
            {
                int mid = (end - start) / 2 + start;
                System.out.format("Slicing to: <%s,%s> and <%s,%s>%n", start, mid, mid + 1, end);
                invokeAll(new ForkAddFive(sourceArray, start, mid, output),
                        new ForkAddFive(sourceArray, mid + 1, end, output));
            }
        }

        protected void computeWithoutSlicing()
        {
            for (int i = start; i <= end; i++)
            {
                output[i] = sourceArray[i] + 5;
            }
        }
    }
}
