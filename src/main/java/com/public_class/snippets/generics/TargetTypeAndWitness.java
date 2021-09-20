package com.public_class.snippets.generics;

import java.util.Collections;
import java.util.List;

@SuppressWarnings({"WeakerAccess", "RedundantTypeArguments"})
public class TargetTypeAndWitness
{
    /**
     * public static final <T> List<T> emptyList() {
     * return (List<T>) EMPTY_LIST;
     * }
     */

    public static void main(String[] args)
    {
        // note here, compiler deduced the type T is String not a List<String>
        List<String> list = Collections.emptyList();

        // You can also use redundant type Witness
        List<String> anotherList = Collections.<String>emptyList(); // T is always before name of method

        // it works only from Java8 in the context of method arguments, i.e
        // foo(Collections.emptyList()) will not work in Java <8 if foo(List<String> list)
        // compiler was not ready to infer type from method arguments
    }
}
