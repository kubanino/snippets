package com.public_class.snippets.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

// http://tutorials.jenkov.com/java-nio/channels.html
// // Kubanino @ https://public-class.com/
public class ScatterGatherBufferExample
{
    public static void main(String[] args) throws IOException // for some convenience this exception is here
    {
        RandomAccessFile inFile;
        RandomAccessFile outFile;
        try
        {
            inFile = new RandomAccessFile("data/nio-data-scatter.txt", "rw");
            outFile = new RandomAccessFile("data/nio-data-scatter-out.txt", "rw");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
            return;
        }
        FileChannel inChannel = inFile.getChannel(); // Here you get channel
        FileChannel outChannel = outFile.getChannel(); // Here you get channel
        ByteBuffer headBuff = ByteBuffer.allocate(2); // header
        ByteBuffer contentBuff = ByteBuffer.allocate(4); // pin code

         inChannel.read(headBuff); // it reads a portion of data, then moves file cursor
         inChannel.read(contentBuff); // it reads a portion of data, then moves file cursor

        ByteBuffer[] scattered = {headBuff, contentBuff};
        long bytesRead = inChannel.read(scattered);
        while (bytesRead != -1)
        {
            System.out.println("\nRead " + bytesRead);
            headBuff.flip();
            contentBuff.flip();

            System.out.println("Header:");
            while (headBuff.hasRemaining())
            {
                System.out.print(headBuff.get());
            }
            System.out.println("\nContent:");
            while (contentBuff.hasRemaining())
            {
                System.out.print(contentBuff.get());
            }
            outChannel.write(scattered);
            bytesRead = inChannel.read(scattered);
        }

        inFile.close(); // still need to close file
        outFile.close(); // still need to close file
    }
}
