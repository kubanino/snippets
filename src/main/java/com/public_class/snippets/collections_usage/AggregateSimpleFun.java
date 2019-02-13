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
        Human husband = new Human("Jakub", Human.Sex.MALE, 32);
        Human wife = new Human("Ala", Human.Sex.FEMALE, 55);
        Human mother = new Human("Ala", Human.Sex.FEMALE, 12);

        List<Human> family = new ArrayList<>();

        family.add(husband);
        family.add(wife);
        family.add(mother);

        long femaleHumansCount = family.stream().
                filter(h -> h.getSex().equals(Human.Sex.FEMALE)).
                count();
        int totalAgeOfFemales = family.stream().
                filter(h -> h.getSex().equals(Human.Sex.FEMALE)).
                mapToInt(Human::getAge).
                reduce(0, (l, r) -> l + r);

        System.out.println(femaleHumansCount);
        System.out.println(totalAgeOfFemales);
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
        private final Integer age;

        private Human(String name, Sex sex, Integer age)
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public Sex getSex()
        {
            return sex;
        }

        public String getName()
        {
            return name;
        }

        public Integer getAge()
        {
            return age;
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
                    Objects.equals(name, human.name) &&
                    Objects.equals(age, human.age);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(sex, name, age);
        }

        @Override
        public String toString()
        {
            return new ToStringBuilder(this)
                    .append("sex", sex)
                    .append("name", name)
                    .append("age", age)
                    .toString();
        }
    }
}
