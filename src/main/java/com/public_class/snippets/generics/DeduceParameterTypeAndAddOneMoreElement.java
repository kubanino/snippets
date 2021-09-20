package com.public_class.snippets.generics;

import java.util.ArrayList;
import java.util.List;

public class DeduceParameterTypeAndAddOneMoreElement<T>
{
    // public static final T someType; <-- can not do this as well

    public static void main(String[] args)
    {
        List<Double> listOfDoubles = new ArrayList<>();
        listOfDoubles.add(1D);

        addOneMoreNumberWithValueOne(listOfDoubles);
    }

    public static void addOneMoreNumberWithValueOne(List<?> listOfNumbers)
    {
        addOneMoreNumberWithValueOneHelper(listOfNumbers);
    }

    private static <T> void addOneMoreNumberWithValueOneHelper(List<T> listOfNumbers)
    {
       // You would expect reflection things to get type of that collection, but You can not - type erasure in action
    }
}

