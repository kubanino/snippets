package com.public_class.snippets.patterns.inner_fun;

public interface HumanFemale extends Female
{
    default void sayHi()
    {
        System.out.println("hello");
    }
}
