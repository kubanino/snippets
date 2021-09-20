package com.public_class.snippets.streams;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Stream.of;

@SuppressWarnings("UnusedAssignment")
public class CleanFunctionsInStreams
{
    private static final String SPACES = "\\s";
    private final static String TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vel congue " +
            "lacus. Cras porttitor volutpat augue at lacinia. Maecenas lobortis odio sodales, commodo ante sed, " +
            "condimentum nulla. Vivamus non nulla non arcu semper volutpat. Aliquam non lacus eleifend, convallis " +
            "lacus et, elementum mauris. Mauris sit amet dui tellus. Nulla gravida sodales metus, eget ultricies " +
            "tellus. Vestibulum aliquet leo nec erat mattis, sit amet mattis mi vulputate. Quisque fermentum mattis " +
            "lorem a ultrices. Cras rhoncus dui id ipsum tristique, sit amet blandit nisl efficitur. Fusce tempor ac " +
            "est eget laoreet. Vivamus viverra tincidunt elit, ac vulputate massa pharetra a. Nam cursus tempor risus" +
            " non feugiat. Sed venenatis id ligula suscipit lobortis.";

    private static final Long ONE = 1L;

    public static void main(String[] args)
    {
        final Map<String, Long> wordCountModified = new HashMap<>();
        final Map<String, Long> wordCountWithNewReferenceAssigned;

        badPractise(wordCountModified);
        wordCountWithNewReferenceAssigned = goodPractise();

        if (wordCountModified.equals(wordCountWithNewReferenceAssigned))
        {
            System.out.println("Both results are equal, so what is the big deal?");
        }

        for (Map.Entry<String, Long> entry : wordCountWithNewReferenceAssigned.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private static void badPractise(final Map<String, Long> inputReference)
    {
        of(TEXT.split(SPACES)).forEach(word -> inputReference.merge(word, ONE, Long::sum));
    }

    private static Map<String, Long> goodPractise()
    {
        return of(TEXT.split(SPACES)).collect(groupingBy(p -> p, counting()));
    }
}
