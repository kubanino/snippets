package com.public_class.snippets.patterns.self_mimick_builder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.public_class.snippets.patterns.self_mimick_builder.ArtisticHammer.DEFAULT_COLOR;
import static com.public_class.snippets.patterns.self_mimick_builder.Hammer.DEFAULT_LENGTH;
import static com.public_class.snippets.patterns.self_mimick_builder.Tool.DEFAULT_WEIGHT;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ArtisticHammerTest
{
    private static final int LENGTH = 20;
    private static final String COLOR = "GREEN";
    private static final Double WEIGHT = 44D;

    @Test
    public void shouldBeAbleToCreateArtisticHammerWithAllParametersWhenUsingLowestLevelBuilder()
    {
        // when
        final ArtisticHammer result = new ArtisticHammer.Builder().
                withColor(COLOR).
                withLength(LENGTH).
                withWeight(WEIGHT).
                build();

        // then
        assertEquals(LENGTH, result.getLength());
        assertEquals(WEIGHT, result.getWeight());
        assertEquals(COLOR, result.getColor());
    }

    @Test
    public void shouldBeAbleToCreateArtisticHammerWhenBuilderIsCalledWithDifferentOrder()
    {
        // when
        final ArtisticHammer result = new ArtisticHammer.Builder().
                withWeight(WEIGHT).
                withColor(COLOR).
                withLength(LENGTH).
                build();

        // then
        assertEquals(LENGTH, result.getLength());
        assertEquals(WEIGHT, result.getWeight());
        assertEquals(COLOR, result.getColor());
    }

    @Test
    public void shouldBuildArtisticHammerWithDefaultParametersWhenPassingNoParametersUsingBuilderMethods()
    {
        // when
        final ArtisticHammer result = new ArtisticHammer.Builder().build();

        // then
        assertEquals(DEFAULT_LENGTH, result.getLength());
        assertEquals(DEFAULT_WEIGHT, result.getWeight());
        assertEquals(DEFAULT_COLOR, result.getColor());
    }

}
