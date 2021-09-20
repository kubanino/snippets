package com.public_class.snippets.interfaces;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    RetentionPolicy.RUNTIME
    Annotations are to be recorded in the class file by the compiler and retained by the VM at run time, so they may be
    read reflectively

    RetentionPolicy.CLASS
    Annotations are to be recorded in the class file by the compiler but need not be retained by the VM at run time.
    This is the default behavior.

    RetentionPolicy.SOURCE
    Annotations are to be discarded by the compiler.
 */

/*
    ElementType.TYPE
    Class, interface (including annotation type), or enum declaration

    The rest is ez
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SomeAnnotation
{
}

@SomeAnnotation
class SomeClass
{
    public static void main(String[] args)
    {
        SomeClass someClass = new SomeClass();
        boolean annotatedWithSomeAnnotation = isAnnotatedWithSomeAnnotation(someClass);

        System.out.println(annotatedWithSomeAnnotation);
    }

    private static boolean isAnnotatedWithSomeAnnotation(Object object)
    {
        Class<?> aClass = object.getClass(); // note joker, cause it can be any type

        return aClass.isAnnotationPresent(SomeAnnotation.class);
    }
}
