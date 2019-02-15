package com.public_class.snippets.collections_usage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.sort;

// https://docs.oracle.com/javase/tutorial/collections/streams/parallelism.html
public class FunnyExampleFromOracleDocs
{
    public static void main(String[] args)
    {
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> listOfIntegers =
                new ArrayList<>(Arrays.asList(intArray));

        System.out.println("listOfIntegers:");
        listOfIntegers
                .stream()
                .forEach(e -> System.out.print(e + " "));
        System.out.println("Sorted like that, because it sustains the order\n");

        System.out.println("listOfIntegers sorted in reverse order:");
        Comparator<Integer> normal = Integer::compare;
        Comparator<Integer> reversed = normal.reversed(); // btw, nice usage of comparators reverse
        sort(listOfIntegers, reversed);
        listOfIntegers
                .stream()
                .forEach(e -> System.out.print(e + " "));
        System.out.println("Sorted like that, because You wanted to have it reversed\n");

        System.out.println("Parallel stream");
        listOfIntegers
                .parallelStream()
                .forEach(e -> System.out.print(e + " "));
        System.out.println("Well, it is just parallelism, no guarantees\n");

        System.out.println("Another parallel stream:");
        listOfIntegers
                .parallelStream()
                .forEach(e -> System.out.print(e + " "));
        System.out.println("One more time - tricky stuff\n");

        System.out.println("With forEachOrdered:");
        listOfIntegers
                .parallelStream()
                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("Here, it is parallel, but reversed because You did sort it. " +
                "Order is sustained\n");
    }
}
