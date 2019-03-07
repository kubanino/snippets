package com.public_class.snippets.concurrent;

import static java.lang.System.identityHashCode;

// Kubanino @ https://public-class.com/
public class VariableVisibilityExamples
{
    public static void main(String[] args)
    {
        int someVariable = 66;
        Integer objectVariable = 123;
        ThreadLocal<Integer> localValue = new ThreadLocal<>();
        InheritableThreadLocal<Integer> inheritableThreadLocal = new InheritableThreadLocal<>();
        localValue.set(55);
        inheritableThreadLocal.set(55);

        System.out.println(identityHashCode(inheritableThreadLocal.get())); // note reference

        // it is visible
        new Thread(() -> {
            System.out.println(someVariable);// ok
            System.out.println(objectVariable); // ok
            System.out.println(localValue.get()); // null
            System.out.println(inheritableThreadLocal.get()); // ok
            System.out.println(identityHashCode(inheritableThreadLocal.get())); // ok
            System.out.println(inheritableThreadLocal); // same reference
        }).start();

        //
        new Thread(() -> {
            int anotherVariable = 55;
            new Thread(() -> {
                // able to print both variables
                System.out.println(anotherVariable);// ok
                System.out.println(someVariable);// ok
                System.out.println(objectVariable);// ok
                System.out.println(localValue.get());// null
                System.out.println(inheritableThreadLocal.get());// ok
                System.out.println(identityHashCode(inheritableThreadLocal.get()));// ok
                System.out.println(inheritableThreadLocal);// // same reference
            }).start();
        }).start();
    }
}
