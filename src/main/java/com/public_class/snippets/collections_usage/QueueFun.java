package com.public_class.snippets.collections_usage;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import static java.lang.System.out;

public class QueueFun
{
    private static final Random RANDOM = new Random();

    public static void main(String[] args)
    {
        Queue<String> queue = new PriorityQueue<>();

        for (int i = 0; i < 10; i++)
        {
            final String generatedString = new String(new char[]{(char) (75 - RANDOM.nextInt(10))});
            out.println("Generated string: " + generatedString);
            queue.add(generatedString);
        }
        out.println("Priority queue will use natural comparison");
        queue.forEach(out::println);

        out.println("Let's peek the first element, twice");
        out.println(queue.peek());
        out.println(queue.peek());

        out.println("Let's pool the first element 3 times");
        out.println(queue.poll());
        out.println(queue.poll());
        out.println(queue.poll());

        out.println("Ok, so does offer work?");
        out.println(queue.offer("Hello Man"));

        out.println("What will happen if bounded queue is created?");
        Queue<String> boundedQueue = new ArrayBlockingQueue<>(4);

        for (int i = 0; i < 6; i++)
        {
            out.println("Sucess in adding? " + boundedQueue.offer("Test"));
        }
        boundedQueue.add("Exception");
    }
}
