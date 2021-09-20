package com.public_class.snippets.patterns.self_mimick_builder;

public abstract class Hammer extends Tool
{
    public static final int DEFAULT_LENGTH = 13;

    private int length;

    Hammer(final Hammer.Builder<?> builder)
    {
        super(builder);
        length = builder.length;
    }

    public int getLength()
    {
        return length;
    }

    // Hammer Builder accepts "T" of type "ArtisticHammer.Builder" and passes again the type to Tool.Builder
    public abstract static class Builder<T extends Hammer.Builder<T>> extends Tool.Builder<T>
    {
        int length = DEFAULT_LENGTH;

        public T withLength(int length)
        {
            this.length = length;
            return self();
        }
    }
}
