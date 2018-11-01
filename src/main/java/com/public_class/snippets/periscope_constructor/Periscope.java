package com.public_class.snippets.periscope_constructor;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class Periscope
{
    private String name;
    private String weight;
    private String height;

    public Periscope(final String height)
    {
        this(height, EMPTY);
    }

    public Periscope(final String height, final String name)
    {
        this(height, name, EMPTY);
    }

    public Periscope(final String height, final String name, final String weight)
    {
        this.name = name;
        this.weight = weight;
        this.height = height;
    }

    public String getName()
    {
        return name;
    }

    public String getWeight()
    {
        return weight;
    }

    public String getHeight()
    {
        return height;
    }
}
