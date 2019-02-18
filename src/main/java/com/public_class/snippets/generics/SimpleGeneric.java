package com.public_class.snippets.generics;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

/**
 * @param <T> E - Element (used extensively by the Java Collections Framework)
 *            K - Key
 *            N - Number
 *            T - Type
 *            V - Value
 *            S,U,V etc. - 2nd, 3rd, 4th types
 */


// Kubanino @ https://public-class.com/
public class SimpleGeneric<T, S>
{
    private final T innerObject;
    private final S anotherInnerObject;

    public SimpleGeneric(T innerObject, S anotherInnerObject)
    {
        this.innerObject = innerObject;
        this.anotherInnerObject = anotherInnerObject;
    }

    public T getInnerObject()
    {
        return innerObject;
    }

    public S getAnotherInnerObject()
    {
        return anotherInnerObject;
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
        SimpleGeneric<?, ?> that = (SimpleGeneric<?, ?>) o;
        return Objects.equals(innerObject, that.innerObject) &&
                Objects.equals(anotherInnerObject, that.anotherInnerObject);
    }

    @Override
    public int hashCode()
    {

        return Objects.hash(innerObject, anotherInnerObject);
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                .append("innerObject", innerObject)
                .append("anotherInnerObject", anotherInnerObject)
                .toString();
    }

    // and a simple usage
    public static void main(String[] args)
    {
        final SimpleGeneric<String, Integer> something = new SimpleGeneric<>("I am", 25);
        System.out.println(something);
    }
}
