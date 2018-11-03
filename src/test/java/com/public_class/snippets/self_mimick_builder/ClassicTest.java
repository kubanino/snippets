package com.public_class.snippets.self_mimick_builder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ClassicTest
{
    private static final String NAME = "name";
    private static final String SURNAME = "surname";

    @Test
    public void shouldConstructWhenUsingBuilder()
    {
        // when
        final Classic result = new Classic.Builder().
                withName(NAME).
                withSurname(SURNAME).
                build();

        // then
        assertEquals(NAME, result.getName());
        assertEquals(SURNAME, result.getSurname());
    }
}
