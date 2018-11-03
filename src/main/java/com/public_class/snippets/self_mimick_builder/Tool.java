package com.public_class.snippets.self_mimick_builder;

public abstract class Tool
{
    public static final Double DEFAULT_WEIGHT = 12D;

    private Double weight;

    Tool(Builder<?> builder)
    {
        weight = builder.weight;
    }

    public Double getWeight()
    {
        return weight;
    }

    // "T" is the lowest level ArtisticHammer.Builder
    abstract static class Builder<T extends Builder<T>>
    {
        Double weight = DEFAULT_WEIGHT;

        public T withWeight(Double weight)
        {
            this.weight = weight;
            return self();
        }

        abstract Tool build();

        protected abstract T self();
    }
}
