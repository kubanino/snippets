package com.public_class.snippets.generics;

import java.lang.reflect.Type;

// Kubanino @ https://public-class.com/
public class ThrowGenericExceptions<T extends Exception>
{

    public static void main(String[] args)
    {
        ThrowGenericExceptions<ClassNotFoundException> lovelyObject = new ThrowGenericExceptions<>();
        try
        {
            lovelyObject.throwMeSomeFancyException();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public void throwMeSomeFancyException() throws T
    {
        // as long as You give him T type, you can throw this exception, fine
    }

}

