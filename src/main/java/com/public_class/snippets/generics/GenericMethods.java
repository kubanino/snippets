package com.public_class.snippets.generics;

@SuppressWarnings({"WeakerAccess", "RedundantTypeArguments"})
public class GenericMethods
{
    public static <T extends Number, S extends Number> double addThemUp(T first, S second)
    {
        return first.doubleValue() + second.doubleValue();
    }

    public static void main(String[] args)
    {
        Long cash = 1L;
        Float equities = 3F;

        // with full syntax, the invoke looks like that:
        System.out.println(GenericMethods.<Long, Float>addThemUp(cash, equities));

        // You do not actually need some parts of it, as compiler will deduce the type
        System.out.println(addThemUp(cash, equities));
    }
}
