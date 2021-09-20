package com.public_class.snippets.generics;

import java.util.Arrays;
import java.util.List;

public class UsageOfCrazyJokerWildcard
{
    // List<String> is a subtype of List<?> (joker without any bounds)
    public static void printListContent(List<?> list)
    {
        for (Object elem : list)
        {
            System.out.println(elem);
        }
    }

    public static void main(String[] args)
    {
        List<String> listOfStrings = Arrays.asList("Jakub", "Alicja", "Michal");
        List<Integer> listOfIntegers = Arrays.asList(1, 2, 3);

        printListContent(listOfStrings);
        printListContent(listOfIntegers);

        // note: List<?> is not the same as List<Object>. List<?> can not accept any element (except of null)!
    }
}
