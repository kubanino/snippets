package com.public_class.snippets.collections_usage;

import java.util.EnumSet;
import java.util.Set;

// Kubanino @ https://public-class.com/
public class EnumSetExample
{
    private enum Days
    {
        MON, TUE, WED, THU, FRI, SAT, SUN;
    }

    public static void main(String[] args)
    {
        Set<Days> workingDays = EnumSet.range(Days.MON, Days.FRI);
        workingDays.forEach(System.out::println);
    }
}
