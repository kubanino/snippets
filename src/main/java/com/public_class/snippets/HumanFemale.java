package com.public_class.snippets;

public interface HumanFemale extends Female
{
    default void sayHi()
    {
        System.out.println("hello");
    }
}
