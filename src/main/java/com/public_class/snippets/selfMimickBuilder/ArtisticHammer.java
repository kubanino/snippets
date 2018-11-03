package com.public_class.snippets.selfMimickBuilder;

public class ArtisticHammer extends Hammer
{
    private String color;

    ArtisticHammer(ArtisticHammer.Builder builder)
    {
        super(builder);
        this.color = builder.color;
    }

    // ArtisticHammer.Builder extends upper class's builder, passing his own type
    public static class Builder extends Hammer.Builder<ArtisticHammer.Builder>
    {
        String color;

        public ArtisticHammer.Builder withColor(String color)
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
