package com.public_class.snippets.streams;

import java.util.function.Consumer;

// Kubanino @ https://public-class.com/
public class BootstrapMethod
{
    public static void main(String[] args)
    {
        Consumer<Integer> consumer = s -> System.out.println(s);
        consumer.accept(5);

        /*
            INVOKEDYNAMIC accept()Ljava/util/function/Consumer; [
            // handle kind 0x6 : INVOKESTATIC
            java/lang/invoke/LambdaMetafactory.metafactory(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
            // arguments:
            (Ljava/lang/Object;)V,
            // handle kind 0x6 : INVOKESTATIC
            com/public_class/snippets/streams/BootstrapMethod.lambda$main$0(Ljava/lang/Integer;)V,
            (Ljava/lang/Integer;)V
            ]
         */

        /*
            // access flags 0x100A
            private static synthetic lambda$main$0(Ljava/lang/Integer;)V
            L0
            LINENUMBER 10 L0
            GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
            ALOAD 0
            INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/Object;)V
            RETURN
            L1
            LOCALVARIABLE s Ljava/lang/Integer; L0 L1 0
            MAXSTACK = 2
            MAXLOCALS = 1
            }
         */

        // What is returned is CallSite
        // C:/Program Files/Java/jdk1.8.0_181/src.zip!/java/lang/invoke/CallSite.java:91
        // that crap contains MethodHandle
    }
}
