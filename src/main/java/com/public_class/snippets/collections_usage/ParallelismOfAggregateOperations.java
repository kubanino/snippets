package com.public_class.snippets.collections_usage;

import com.public_class.snippets.collections_usage.AggregateSimpleFun.Human;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

// Kubanino @ https://public-class.com/
public class ParallelismOfAggregateOperations
{
    private static final Random RANDOM = new Random();

    public static void main(String[] args)
    {
        // create a random set of 12 rows (! huge amount)
        List<Long> someInput = new ArrayList<>();

        for (int i = 0; i < 12; i++)
        {
            someInput.add((long)RANDOM.nextInt(500));
        }
        System.out.println(someInput);

        Long total = someInput.parallelStream().count();

        // You can use collector, if its characteristics is Collector.Characteristics.CONCURRENT
        Human husband = new Human("Jakub", Human.Sex.MALE, 32);
        Human wife = new Human("Ala", Human.Sex.FEMALE, 55);
        Human mother = new Human("Ala", Human.Sex.FEMALE, 12);

        List<Human> family = new ArrayList<>();

        family.add(wife);
        family.add(husband);
        family.add(mother);

        Map<Human.Sex, Integer> ageBySex = family.
                parallelStream().
                collect(groupingByConcurrent(Human::getSex, ConcurrentHashMap::new, summingInt(Human::getAge)));

        System.out.println(ageBySex);

    }
}
