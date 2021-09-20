package com.public_class.snippets.generics;

import java.util.List;

public class WildcardTypeCaptureExample
{
    // the problem is, that list is declared as List<?> and You can not put anything there
    public static void resetFirstValue(List<?> list)
    {
        // list.set(0, list.get(0));
    }

    public static void resetFirstValueWithTypeCapture(List<?> list)
    {
        resetFirstValueWithTypeCaptureHelper(list);
    }

    private static <T> void resetFirstValueWithTypeCaptureHelper(List<T> list)
    {
        list.set(0, list.get(0));
    }
}
