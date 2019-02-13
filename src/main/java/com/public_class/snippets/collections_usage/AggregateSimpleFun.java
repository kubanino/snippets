package com.public_class.snippets.collections_usage;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Kubanino @ https://public-class.com/
public class AggregateSimpleFun
{
    public static void main(String[] args)
    {
        Human husband = new Human("Jakub", Human.Sex.MALE);
        Human wife = new Human("Ala", Human.Sex.FEMALE);
        Human mother = new Human("Ala", Human.Sex.FEMALE);

        List<Human> family = new ArrayList<>();

        family.add(husband);
        family.add(wife);
        family.add(mother);

        long femaleHumansCount = family.stream().filter(h -> h.getSex().equals(Human.Sex.FEMALE)).count();

        System.out.println(femaleHumansCount);
    }

    private static class Human
    {
        enum Sex
        {
            MALE,
            FEMALE
        }

        private final Sex sex;

        private final String name;

        private Human(String name, Sex sex)
        {
            this.name = name;
            this.sex = sex;
        }

        public Sex getSex()
        {
            return sex;
        }

        public String getName()
        {
            return name;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || getClass() != o.getClass())
            {
                return false;
            }
            Human human = (Human) o;
            return sex == human.sex &&
                    Objects.equals(name, human.name);
        }

        @Override
        public int hashCode()
        {

            return Objects.hash(sex, name);
        }

        @Override
        public String toString()
        {
            return new ToStringBuilder(this)
                    .append("sex", sex)
                    .append("name", name)
                    .toString();
        }
    }
}
