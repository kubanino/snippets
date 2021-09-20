package com.public_class.snippets.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

// http://tutorials.jenkov.com/java-nio/channels.html
public class SocketChannelExample
{
    public static void main(String[] args) throws IOException // for some convenience this exception is here
    {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("https://www.google.pl/", 80));

        ByteBuffer buffer = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buffer);

        while (bytesRead != -1)
        {
            buffer.flip();
            while (buffer.hasRemaining())
            {
                System.out.print(buffer.get());
            }
            bytesRead = socketChannel.read(buffer);
        }
    }
}
