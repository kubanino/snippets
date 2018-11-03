package com.public_class.snippets.selfMimickBuilder;

public abstract class Hammer extends Tool
{
    private int length;

    Hammer(Hammer.Builder<?> builder)
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
        int length;

        public T withLength(int length)
        {
            this.length = length;
            return self();
        }
    }
}
