package com.public_class.snippets.streams;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

// https://www.javaworld.com/article/2860079/invokedynamic-101.html
public class MethodHandleExample
{
    public static void main(String[] args)
    {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try
        {
            MethodHandle methodHandle = lookup.findStatic(MethodHandleExample.class, "hello", java.lang.invoke
                    .MethodType.methodType(void.class));
            methodHandle.invokeExact();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
        }
    }

    public static void hello()
    {
        System.out.println("hello");
    }
}
