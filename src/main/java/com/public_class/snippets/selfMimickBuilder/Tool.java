package com.public_class.snippets.selfMimickBuilder;

public abstract class Tool
{
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
        Double weight;

        public T withWeight(Double weight)
        {
            this.weight = weight;
            return self();
        }

        abstract Tool build();

        protected abstract T self();
    }
}
