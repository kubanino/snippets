package com.public_class.snippets.nio;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileLocksExample
{
    public static void main(String[] args)
    {
        Path path = Paths.get("data/findMeWithPath.txt");
        FileChannel channel = null;

        try
        {
            channel = FileChannel.open(path, StandardOpenOption.WRITE);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            FileLock lock = channel.lock();
            System.out.println("Obtained lock: " + lock);
            System.out.println("Acquired by: " + lock.acquiredBy());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
