package com.public_class.snippets.patterns.inner_fun;

public class Mother extends GrandMother
{
    public static final String HI = "Hi";

    public class CheerfulMother
    {
        public void sayHi()
        {
            System.out.println(HI);
        }
    }
}
