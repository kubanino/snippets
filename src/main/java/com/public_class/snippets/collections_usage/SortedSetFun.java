package com.public_class.snippets.collections_usage;

import java.util.SortedSet;
import java.util.TreeSet;

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

        dictionary.subSet("C", "J").clear(); // "J" is not included, that is why J*** are still available

        System.out.println(dictionary);
    }
}
