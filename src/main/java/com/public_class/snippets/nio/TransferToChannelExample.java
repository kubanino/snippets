package com.public_class.snippets.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

// http://tutorials.jenkov.com/java-nio/channels.html
// // Kubanino @ https://public-class.com/
public class TransferToChannelExample
{
    public static void main(String[] args) throws IOException // for some convenience this exception is here
    {
        FileChannel input = new RandomAccessFile("data/transferMeSomewhere.txt", "rw").getChannel();
        FileChannel output = new RandomAccessFile("data/iWillReceiveContent.txt", "rw").getChannel();

        input.transferTo(0, input.size(), output);
    }
}
