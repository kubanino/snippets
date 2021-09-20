package com.public_class.snippets.collections_usage;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackVsQueue
{
    public static void main(String[] args)
    {
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();

        // populate
        for (int i = 0; i < 10; i++)
        {
            stack.push(i);
            queue.offer(i);
        }
        // peek does nothing on both
        System.out.println("PEEK DOES NOTHING ON BOTH:");
        for (int i = 0; i < 10; i++)
        {
            System.out.println(stack.peek());
            System.out.println(stack.peek());
        }
        // read
        System.out.println("STACK:");
        for (int i = 0; i < 10; i++)
        {
            System.out.println(stack.pop());
        }
        System.out.println("QUEUE:");
        for (int i = 0; i < 10; i++)
        {
            System.out.println(queue.poll());
        }
    }
}
