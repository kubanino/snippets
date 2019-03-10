package com.public_class.snippets.nio;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

// http://tutorials.jenkov.com/java-nio/path.html
// Kubanino @ https://public-class.com/
public class PathExample
{
    public static void main(String[] args)
    {
        // tell me where am I?
        Path currentPath = Paths.get(".");
        Path normalizedCurrentPath = currentPath.toAbsolutePath().normalize();
        System.out.println(normalizedCurrentPath); // normalize will get rid of ".", ".." and so on

        // get findMeWithPath.txt file
        String rootedPath = normalizedCurrentPath + File.separator + "data" + File.separator + "findMeWithPath.txt";
        System.out.println(rootedPath);

        // get upper path
        Path upperPath = Paths.get("..");
        System.out.println(upperPath.toAbsolutePath().normalize());
    }
}
