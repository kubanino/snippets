package com.public_class.snippets.innerFun;

public interface HumanFemale extends Female
{
    default void sayHi()
    {
        System.out.println("hello");
    }
}
