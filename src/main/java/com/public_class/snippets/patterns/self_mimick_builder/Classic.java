package com.public_class.snippets.patterns.self_mimick_builder;

public class Classic
{
    private String name;
    private String surname;

    private Classic(final String name, final String surname)
    {
        this.name = name;
        this.surname = surname;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    static class Builder
    {
        private String name;
        private String surname;

        public Builder()
        {
        }

        public Builder withName(final String name)
        {
            this.name = name;
            return this;
        }

        public Builder withSurname(final String surname)
        {
            this.surname = surname;
            return this;
        }

        public Classic build()
        {
            return new Classic(name, surname);
        }
    }
}
