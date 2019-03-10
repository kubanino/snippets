package com.public_class.snippets.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

// https://www.concretepage.com/java/example-charset-java-nio
// // Kubanino @ https://public-class.com/
public class CharsetExample
{
    public static void main(String[] args)
    {
        Charset charset = Charset.forName("UTF-8");
        System.out.println(charset.displayName());
        System.out.println(charset.canEncode()); // will tell You, if it can encode or not

        String toBeDecoded = "Something to decode";
        ByteBuffer buffer = ByteBuffer.wrap(toBeDecoded.getBytes()); // look how handy is that: String -> byte[]
        CharBuffer charBuffer = charset.decode(buffer);
        ByteBuffer encodedBuffer = charset.encode(charBuffer);

        while (encodedBuffer.hasRemaining())
        {
            System.out.println((char) encodedBuffer.get());
        }
        encodedBuffer.clear();
        charBuffer.clear();
    }
}
