package com.public_class.snippets.periscope_constructor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PeriscopeTest
{
    private static final String EMPTY_PERISCOPE_VALUE = "";

    @Test
    public void shouldConstructObjectWithEmptyWeightAndNameWhenUsingPeriscopeConstructor()
    {
        // given
        final String height = "too high";

        // when
        final Periscope periscope = new Periscope(height);

        // then
        assertEquals(height, periscope.getHeight());
        assertEquals(EMPTY_PERISCOPE_VALUE, periscope.getName());
        assertEquals(EMPTY_PERISCOPE_VALUE, periscope.getWeight());
    }
}
