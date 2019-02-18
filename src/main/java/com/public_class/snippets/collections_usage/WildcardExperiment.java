package com.public_class.snippets.collections_usage;

import java.util.ArrayList;
import java.util.List;

// Kubanino @ https://public-class.com/
public class WildcardExperiment
{
    public static void main(String[] args)
    {
        List<?> wildcardList = new ArrayList<>();

        wildcardList.add(null);
        wildcardList.add(null); // you can add only null

        System.out.println(wildcardList);
    }
}
