package com.public_class.snippets.collections_usage;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

// Kubanino @ https://public-class.com/
public class TreeSetVsTreeMap
{
    public static final Map<String, String> TREE_MAP = new TreeMap<>();
    public static final Set<String> TREE_SET = new TreeSet<>();

    public static final String KRAKOW = "Krakow";
    public static final String WROCLAW = "Wroclaw";
    public static final String WARSZAWA = "Warszawa";

    public static final String OPEL = "Opel";
    public static final String BMW = "BMW";
    public static final String FIAT = "Fiat";

    static
    {
        TREE_SET.add(KRAKOW);
        TREE_SET.add(WROCLAW);
        TREE_SET.add(WARSZAWA);

        TREE_MAP.put(BMW, KRAKOW);
        TREE_MAP.put(OPEL, WROCLAW);
        TREE_MAP.put(FIAT, WARSZAWA);
    }

    public static void main(String[] args)
    {
        System.out.println(TREE_MAP); // TreeMap is sorted by keys
        System.out.println(TREE_SET); // TreeSet is sorted by its values
    }
}
