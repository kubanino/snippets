package com.public_class.snippets.concurrent;

// Kubanino @ https://public-class.com/
@SuppressWarnings("UnusedReturnValue")
public class StringBufferVsStringBuilder
{
    private static final String INPUT_TEXT = "I will be the cause for this Thread misery!";

    private static final int REPETITIONS = 1_000_000;

    private static final char[] INPUT_CHARS = INPUT_TEXT.toCharArray();

    private static final StringBuffer STRING_BUFFER = new StringBuffer();

    private static final StringBuilder STRING_BUILDER = new StringBuilder();

    private static String rewriteMeUsingStringBuilder()
    {
        final String result;

        for (int i = 0; i < REPETITIONS; i++)
        {
            for (char ch : INPUT_CHARS)
            {
                STRING_BUILDER.append(ch);
            }
        }
        result = STRING_BUILDER.toString();

        return result;
    }

    private static String rewriteMeUsingStringBuffer()
    {
        final String result;

        for (int i = 0; i < REPETITIONS; i++)
        {
            for (char ch : INPUT_CHARS)
            {
                STRING_BUFFER.append(ch);
            }
        }
        result = STRING_BUFFER.toString();

        return result;
    }

    public static void main(String[] args)
    {
        long executionTimeStart = System.currentTimeMillis();

        rewriteMeUsingStringBuilder();
        long executionTimeAfterStringBuilder = System.currentTimeMillis();

        rewriteMeUsingStringBuffer();
        long executionTimeAfterStringBuffer = System.currentTimeMillis();

        System.out.println("StringBuilder execution time: "
                + (executionTimeAfterStringBuilder - executionTimeStart) + "[ms]");
        System.out.println("StringBuffer execution time: "
                + (executionTimeAfterStringBuffer - executionTimeAfterStringBuilder) + "[ms]");

        System.out.println("The difference comes mainly from the extra synchronization used in" +
                "StringBuffer");
    }
}
