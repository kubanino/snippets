package com.public_class.snippets.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * You can prevent it from happening passing -Xlint:unchecked to VM options
 */

// Kubanino @ https://public-class.com/
public class RawTypeProblem
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        List aList = new ArrayList(); // without any parametrization, im fine with doing so!?
        aList.add("Something"); // here is the first warning

        Object o = aList.get(0); // it has to be an object!
        String something = (String) o;

        System.out.println(something); // just terrible
    }
}
