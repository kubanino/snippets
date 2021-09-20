package com.public_class.snippets.exceptions;

public class ExceptionTranslator
{
    private static IllegalStateException translateToIllegalStateException(Throwable exception)
    {
        return new IllegalStateException(exception);
    }

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args)
    {
        Throwable exception = new NullPointerException("Well, I am one of a kind. Am I?");
        Throwable translated = translateToIllegalStateException(exception);

        System.out.println("Do not repeat such crazy code at home!");
        System.out.println(translated.getCause().getMessage());
        System.out.println(translated instanceof NullPointerException);
        System.out.println(translated instanceof IllegalStateException);
    }
}
