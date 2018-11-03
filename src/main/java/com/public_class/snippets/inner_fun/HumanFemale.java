package com.public_class.snippets.inner_fun;

public interface HumanFemale extends Female
{
    default void sayHi()
    {
        System.out.println("hello");
    }
}
