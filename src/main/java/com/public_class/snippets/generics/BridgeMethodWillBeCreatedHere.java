package com.public_class.snippets.generics;

// https://docs.oracle.com/javase/tutorial/java/generics/bridgeMethods.html
public class BridgeMethodWillBeCreatedHere<T>
{
    public T data;

    public BridgeMethodWillBeCreatedHere(T data)
    {
        this.data = data;
    }

    public void setData(T data)
    {
        System.out.println("BridgeMethodWillBeCreatedHere.setData");
        this.data = data;
    }
}

/**
 * Because this class extends generic which is already parametrized for Integer, there is a need for bridge method
 */
class BridgeMethodWillBeCreatedHereSubClass extends BridgeMethodWillBeCreatedHere<Integer>
{
    public BridgeMethodWillBeCreatedHereSubClass(Integer data)
    {
        super(data);
    }

    /*
        COMPILER WILL CREATE THIS METHOD - BRIDGE METHOD
         public void setData(Object data) {
            setData((Integer) data);
        }
     */

    public void setData(Integer data)
    {
        System.out.println("BridgeMethodWillBeCreatedHereSubClass.setData");
        super.setData(data);
    }
}
