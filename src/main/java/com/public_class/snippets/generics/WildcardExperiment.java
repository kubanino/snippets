package com.public_class.snippets.generics;

import java.util.ArrayList;
import java.util.List;

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
