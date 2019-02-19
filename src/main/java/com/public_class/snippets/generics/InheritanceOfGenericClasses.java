package com.public_class.snippets.generics;

import java.util.ArrayList;
import java.util.List;

// Kubanino @ https://public-class.com/
public class InheritanceOfGenericClasses
{
    public static void main(String[] args)
    {
        List<Integer> aListOfIntegers = new ArrayList<>();
        List<Number> aListOfNumbers = new ArrayList<>();

        List<? super Integer> aListOfIntegersAndParents = new ArrayList<>();
        List<? extends Number> aListOfNumbersAndChildren = new ArrayList<>();

        aListOfIntegersAndParents = aListOfNumbers; // valid
        aListOfNumbersAndChildren = aListOfIntegers; // valid

        // can not do that: aListOfNumbers = aListOfIntegers;
    }
}
