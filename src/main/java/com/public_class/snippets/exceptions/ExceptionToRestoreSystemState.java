package com.public_class.snippets.exceptions;

// Kubanino @ https://public-class.com/
public class ExceptionToRestoreSystemState
{
    private static void causeBadEffect() throws IllegalAccessException
    {
        System.out.println("Bad method says:\tShould not try to access!");
        throw new IllegalAccessException();
    }

    @SuppressWarnings("RedundantThrows")
    private static void runDumbClientWithHabbitOfCatchingEverything() throws IllegalAccessException
    {
        try
        {
            causeBadEffect();
        }
        catch (IllegalAccessException e)
        {
            System.out.println("Dumb client:\t\tI am super dumb client code, I will handle Your complains, " +
                    "even if it is not part of my responsibility");
        }
    }

    private static void runSmartClientWithGoodHabbits() throws IllegalAccessException
    {
        causeBadEffect();
    }

    private static int monitorBadEffects()
    {
        int badSituationsCounter = 0;

        try
        {
            runDumbClientWithHabbitOfCatchingEverything();
        }
        catch (IllegalAccessException e)
        {
            badSituationsCounter++;
        }
        try
        {
            runSmartClientWithGoodHabbits();
        }
        catch (IllegalAccessException e)
        {
            badSituationsCounter++;
        }
        return badSituationsCounter;
    }

    public static void main(String[] args)
    {
        System.out.println("Monitor:\t\t\tBecause I am the monitor, I will tell You how many system failures we " +
                "had!\n");
        System.out.println("\nMonitor:\t\t\tWell, we had only " + monitorBadEffects() + " failure");
    }
}
