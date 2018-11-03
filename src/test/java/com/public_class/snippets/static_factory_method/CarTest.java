package com.public_class.snippets.static_factory_method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.public_class.snippets.static_factory_method.Car.of;
import static org.junit.Assert.assertEquals;

// Kubanino @ https://public-class.com/
@RunWith(MockitoJUnitRunner.class)
public class CarTest
{
    private static final String BMW = "BMW";

    private static final Car BMW_CAR = of(BMW);

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenBrandIsNotCached()
    {
        // given
        final String brand = "Audi";

        // when
        final Car result = of(brand);
    }

    @Test
    public void shouldReturnInstanceOfBwmWhenPassingMixedCaseBmwString()
    {
        // given
        final String mixedCaseBmw = "BmW";

        // when
        final Car result = of(mixedCaseBmw);

        // then
        assertEquals(BMW_CAR, result);
    }
}
