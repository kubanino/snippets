package com.public_class.snippets.nio;

import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;

// http://tutorials.jenkov.com/java-nio/channels.html
public class SelectorExample
{
    public static void main(String[] args) throws IOException // for some convenience this exception is here
    {
        DatagramChannel datagramChannel = DatagramChannel.open();

        // Create selector
        Selector selector = Selector.open();

        // switch to nonblocking mode
        datagramChannel.configureBlocking(false);

        // classic observer pattern, and here You register the observer with certain interests (OP_WRITE, OP_READ)
        int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE; // keys are bits shifted, so JOIN them

        // register and obtain SelectionKey - handy wrapper
        SelectionKey selectionKey = datagramChannel.register(selector, interestSet);

        // You can later check which interest rate is inside Selector:
        System.out.println(interestSet);

        /*
        boolean isInterestedInAccept  = interestSet & SelectionKey.OP_ACCEPT;
        boolean isInterestedInConnect = interestSet & SelectionKey.OP_CONNECT;
        boolean isInterestedInRead    = interestSet & SelectionKey.OP_READ;
        boolean isInterestedInWrite   = interestSet & SelectionKey.OP_WRITE;
        */

        // however, easier way to do that:
        selectionKey.isReadable();
        selectionKey.isAcceptable();
        selectionKey.isConnectable();
        selectionKey.isWritable();

        // You can access channel and get selector as well
        selectionKey.selector();
        selectionKey.channel();
        selectionKey.attachment();

        // you can create attachment object
        // datagramChannel.register(selector, interestSet, new Object());

        // after You have registered all interested channels in the selector, You can:
        int numberOfReadyChannels = selector.select(); // will block and return You the selected channels

        int numberOfReadySinceLastCheck = selector.select(); // will return number of channels since last check

        if (numberOfReadyChannels > 0)
        {
            Set<SelectionKey> selectionKeys = selector.selectedKeys(); // You can do whatever You want with those

            // imagine, that You want to write something to channels
            for (SelectionKey key : selectionKeys)
            {
                if (key.isWritable())
                {
                    // do some stuff
                    SelectableChannel channel = key.channel(); // as You can see, it has to be casted to proper channel
                }
            }
        }
        selector.wakeup(); // tell other threads, that it is time to wakeup!
        selector.close(); // the end of my work with it
    }
}
