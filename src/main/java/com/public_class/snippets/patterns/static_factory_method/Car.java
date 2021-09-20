package com.public_class.snippets.patterns.static_factory_method;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.Map;

public class Car
{
    private static final String OPEL = "OPEL";
    private static final String BMW = "BMW";

    private String name;

    private static final Map<String, Car> carsCache = new HashMap<>();

    static
    {
        carsCache.put(BMW, new Car(BMW));
        carsCache.put(OPEL, new Car(OPEL));
    }

    private Car(final String name)
    {
        this.name = name;
    }

    public static Car of(final String brand)
    {
        final Car instance = carsCache.get(brand.toUpperCase());

        if (instance == null)
        {
            throw new IllegalArgumentException("Wrong brand, sorry");
        }
        return instance;
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

        Car car = (Car) o;

        return new EqualsBuilder()
                .append(name, car.name)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .toHashCode();
    }
}
