package com.public_class.snippets.nio;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

// http://tutorials.jenkov.com/java-nio/datagram-channel.html
public class DatagramChannelExample
{
    public static void main(String[] args)
    {
        DatagramChannel channel;
        // open datagramChannel
        try
        {
            channel = DatagramChannel.open();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
        // bind to inetAddress
        DatagramSocket socket = channel.socket();
        try
        {
            socket.bind(new InetSocketAddress(9999));
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
        // prepare buffer
        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();

        // try to receive something
        try
        {
            channel.receive(buffer); // it might receive something, but might not since it is UDP
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        // try to "connect" to some address (it is not really a connect, just bind with address)
        try
        {
            channel.connect(new InetSocketAddress("https://www.google.pl", 80));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        int bytesRead;
        try
        {
            bytesRead = channel.read(buffer); //  then You can try to read/write, but remember - it is just attempt
            bytesRead = channel.write(buffer); //  then You can try to read/write, but remember - it is just attempt
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
