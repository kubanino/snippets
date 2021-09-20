package com.public_class.snippets.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

// http://tutorials.jenkov.com/java-nio/asynchronousfilechannel.html
public class AsynchronousFileChannelExample
{
    public static void main(String[] args)
    {
        String someContent = "content of this amazing file";
        Path path = Paths.get("data/someFile.txt");

        // ez write to file with NIO Files
        try
        {
            Files.write(path, someContent.getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // now lets create AsynchronousFileChannel out of it
        AsynchronousFileChannel channel;
        try
        {
            channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        // magic read via Future
        ByteBuffer buffer = ByteBuffer.allocate(48);
        Future<Integer> read = channel.read(buffer, 0);// read to buffer from zero position

        while (!read.isDone())
        { // stupid thread spinning
        }

        buffer.flip(); // now it is in read mode, it was in accept/write mode
        byte[] bufferArray = new byte[buffer.limit()];
        buffer.get(bufferArray);
        String someOutput = new String(bufferArray);
        System.out.println(someOutput);

        // lets write something into it
        buffer.clear();
        buffer.put("another amazing".getBytes());
        buffer.flip(); // flip into reading mode
        try
        {
            Future<Integer> write = channel.write(buffer, 0);
        }
        catch (Exception e)
        {
            // whatever goes wrong
        }

        // read via completion handler - note the attachment object
        buffer.clear();
        buffer.flip();
        channel.read(buffer, 0, null, new CompletionHandler<Integer, Object>()
        {
            @Override
            public void completed(Integer result, Object attachment)
            {
                System.out.println("Completion handler alert - done, with bytes: " + result);
            }

            @Override
            public void failed(Throwable exc, Object attachment)
            {
                // something went wrong
            }
        });
    }
}
