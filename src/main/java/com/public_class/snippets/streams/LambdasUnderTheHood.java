package com.public_class.snippets.streams;

import java.util.function.Consumer;

// https://www.infoq.com/articles/Java-8-Lambdas-A-Peek-Under-the-Hood
// Kubanino @ https://public-class.com/
public class LambdasUnderTheHood
{
    public static void main(String[] args)
    {
        Consumer<Integer> someConsumer = new Consumer<Integer>()
        {
            @Override
            public void accept(Integer o)
            {
                System.out.println(o);
            }
        };
        someConsumer.accept(5);
    }
    /*
    final class com/public_class/snippets/streams/LambdasUnderTheHood$1 implements java/util/function/Consumer  {

  // compiled from: LambdasUnderTheHood.java
  OUTERCLASS com/public_class/snippets/streams/LambdasUnderTheHood main ([Ljava/lang/String;)V
  // access flags 0x8
  static INNERCLASS com/public_class/snippets/streams/LambdasUnderTheHood$1 null null

  // access flags 0x0
  <init>()V
   L0
    LINENUMBER 12 L0
    ALOAD 0
    INVOKESPECIAL java/lang/Object.<init> ()V
    RETURN
   L1
    LOCALVARIABLE this Lcom/public_class/snippets/streams/LambdasUnderTheHood$1; L0 L1 0
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 0x1
  public accept(Ljava/lang/Integer;)V
   L0
    LINENUMBER 16 L0
    GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
    ALOAD 1
    INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/Object;)V
   L1
    LINENUMBER 17 L1
    RETURN
   L2
    LOCALVARIABLE this Lcom/public_class/snippets/streams/LambdasUnderTheHood$1; L0 L2 0
    LOCALVARIABLE o Ljava/lang/Integer; L0 L2 1
    MAXSTACK = 2
    MAXLOCALS = 2

  // access flags 0x1041
  public synthetic bridge accept(Ljava/lang/Object;)V
   L0
    LINENUMBER 12 L0
    ALOAD 0
    ALOAD 1
    CHECKCAST java/lang/Integer
    INVOKEVIRTUAL com/public_class/snippets/streams/LambdasUnderTheHood$1.accept (Ljava/lang/Integer;)V
    RETURN
   L1
    LOCALVARIABLE this Lcom/public_class/snippets/streams/LambdasUnderTheHood$1; L0 L1 0
    MAXSTACK = 2
    MAXLOCALS = 2
}

     */
}
