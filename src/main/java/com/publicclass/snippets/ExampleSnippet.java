package com.publicclass.snippets;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class ExampleSnippet
{
    public static void main(String[] args)
    {
        final Mother mother = new Mother();
        final Mother.CheerfulMother cheerful = mother.new CheerfulMother(); // take a note about this guy

        final ExecutorService service = newCachedThreadPool();
        service.submit(cheerful::sayHi);
    }
}
