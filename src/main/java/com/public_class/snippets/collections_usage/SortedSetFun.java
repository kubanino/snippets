package com.public_class.snippets.collections_usage;

import java.util.SortedSet;
import java.util.TreeSet;

import static java.lang.String.valueOf;

// Kubanino @ https://public-class.com/
public class SortedSetFun
{
    public static void main(String[] args)
    {
        SortedSet<String> dictionary = new TreeSet<>();

        dictionary.add("Jacob");
        dictionary.add("John");
        dictionary.add("Ana");
        dictionary.add("Dan");
        dictionary.add("Chen");
        dictionary.add("Zeus");

        System.out.println(dictionary.tailSet("D"));
        System.out.println(dictionary.headSet("D"));
        describeSet(dictionary);

        dictionary.subSet("C", "J").clear(); // "J" is not included, that is why J*** are still available

        describeSet(dictionary);
    }

    private static void describeSet(SortedSet<String> set)
    {
        for (char ch = 'A'; ch <= 'Z'; ch++)
        {
            System.out.println(ch + ":" + set.subSet(valueOf(ch), valueOf((char) (ch + 1))).toString());
        }
    }
}
