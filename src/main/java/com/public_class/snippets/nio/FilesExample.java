package com.public_class.snippets.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

// http://tutorials.jenkov.com/java-nio/files.html
public class FilesExample
{
    public static void main(String[] args)
    {
        // following Path example, lets work with Files
        Path currentPath = Paths.get(".");
        Path normalizedCurrentPath = currentPath.toAbsolutePath().normalize();
        System.out.println(normalizedCurrentPath); // normalize will get rid of ".", ".." and so on

        try
        {
            Files.walkFileTree(normalizedCurrentPath, new Visitor()); // note, that You have to implement it -
            // anyway, good trade-off
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // perfect example of how we can traverse some directories... yummy
    // You can search, delete, do all the things You would like to do with Files.*****()
    public static class Visitor implements FileVisitor<Path>
    {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
        {
            System.out.println("Pre-visiting directory: " + dir);
            return FileVisitResult.CONTINUE; // lets continue
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
        {
            System.out.println("Visiting file: " + file);
            return FileVisitResult.CONTINUE; // lets continue
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException
        {
            System.out.println("Failed with visiting file: " + file);
            return FileVisitResult.CONTINUE; // lets continue
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException
        {
            System.out.println("Post-visiting directory:" + dir);
            return FileVisitResult.CONTINUE; // lets continue
        }
    }
}
