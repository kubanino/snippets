package com.public_class.snippets.self_mimick_builder;

// Kubanino @ https://public-class.com/
public class ArtisticHammer extends Hammer
{
    public static final String DEFAULT_COLOR = "Purple";

    private String color;

    ArtisticHammer(final ArtisticHammer.Builder builder)
    {
        super(builder);
        this.color = builder.color;
    }

    // ArtisticHammer.Builder extends upper class's builder, passing his own type
    public static class Builder extends Hammer.Builder<ArtisticHammer.Builder>
    {
        String color = DEFAULT_COLOR;

        public ArtisticHammer.Builder withColor(final String color)
        {
            this.color = color;
            return self();
        }

        @Override
        public ArtisticHammer build()
        {
            return new ArtisticHammer(this);
        }

        @Override
        protected ArtisticHammer.Builder self()
        {
            return this;
        }
    }

    public String getColor()
    {
        return color;
    }
}
