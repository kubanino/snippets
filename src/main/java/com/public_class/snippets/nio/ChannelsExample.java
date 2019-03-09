package com.public_class.snippets.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

// http://tutorials.jenkov.com/java-nio/channels.html
// // Kubanino @ https://public-class.com/
public class ChannelsExample
{
    public static void main(String[] args) throws IOException // for some convenience this exception is here
    {
        RandomAccessFile file;
        try
        {
            file = new RandomAccessFile("data/nio-data.txt", "rw");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
            return;
        }
        FileChannel inChannel = file.getChannel(); // Here you get channel
        ByteBuffer buf = ByteBuffer.allocate(8); // And here is Your awesome buffer

        int bytesRead = inChannel.read(buf); // it reads a portion of data, then moves file cursor
        while (bytesRead != -1)
        {
            System.out.println("Read " + bytesRead);
            buf.flip(); // buf was in read mode, after FLIP it is in read mode
            while (buf.hasRemaining()) // whatever is in the buffer is read and buffer pointer is moved
            {
                System.out.print((char) buf.get());
            }
            System.out.println();
            buf.clear();
            bytesRead = inChannel.read(buf); // reads from channel to buffer
        }

        file.close(); // still need to close file
    }
}
