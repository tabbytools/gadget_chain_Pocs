package ysoserial.test;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import nc.bs.framework.common.ComponentMetaVO;
import nc.bs.framework.common.Profiler;
import nc.bs.framework.comn.NetObjectInputStream;
import nc.bs.framework.comn.NetObjectOutputStream;
import nc.bs.framework.rmi.Address;
import nc.bs.framework.rmi.RemoteProxyFactory;
import nc.bs.framework.rmi.SimpleRemoteAddressSelector;

public class RemoteUtil {
    RemoteUtil() {
    }

    public static void writeObject(OutputStream output, Object obj) throws IOException {
        Profiler.enter("serialize");
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        try {
            NetObjectOutputStream objOut = new NetObjectOutputStream(bout, 2);
            objOut.writeObject(obj);
            objOut.finish();
            objOut.flush();
            writeInt(output, bout.size());
        } finally {
            Profiler.leave();
        }

        try {
            Profiler.enter("write to net");
            bout.writeTo(output);
            output.flush();
        } finally {
            Profiler.leave("size=" + (bout.size() + 4));
        }

    }

    public static ByteArrayOutputStream convertObjectToBytes(Object obj, boolean compressed, boolean encrypted) throws IOException {
        Profiler.enter("serialize(convert)");
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        try {
            NetObjectOutputStream objOut = new NetObjectOutputStream(bout, compressed, encrypted, 2);
            objOut.writeObject(obj);
            objOut.finish();
            objOut.flush();
        } finally {
            Profiler.leave();
        }

        return bout;
    }

    public static void writeInt(OutputStream output, int v) throws IOException {
        byte[] bytes = new byte[]{(byte)(v >>> 24 & 255), (byte)(v >>> 16 & 255), (byte)(v >>> 8 & 255), (byte)(v >>> 0 & 255)};
        output.write(bytes);
    }

    public static Object readObject(InputStream in, boolean[] retValue) throws IOException, ClassNotFoundException {
        Profiler.enter("read from net");
        BufferedInputStream bin = new BufferedInputStream(in);
        byte[] bytes = null;

        try {
            int len = readInt(bin);
            bytes = new byte[len];

            int readLen;
            int tmpLen;
            for(readLen = bin.read(bytes); readLen < len; readLen += tmpLen) {
                tmpLen = bin.read(bytes, readLen, len - readLen);
                if (tmpLen < 0) {
                    break;
                }
            }

            if (readLen < len) {
                throw new EOFException("ReadObject EOF error readLen: " + readLen + " expected: " + len);
            }
        } finally {
            Profiler.leave("size=" + (bytes == null ? -1 : bytes.length));
        }

        Profiler.enter("deserialize");

        Object var16;
        try {
            NetObjectInputStream objIn = new NetObjectInputStream(new ByteArrayInputStream(bytes));
            if (retValue != null) {
                retValue[0] = objIn.isCompressed();
                retValue[1] = objIn.isEncrypted();
            }

            var16 = objIn.readObject();
        } finally {
            Profiler.leave();
        }

        return var16;
    }

    public static int readInt(InputStream in) throws IOException {
        int ch1 = in.read();
        int ch2 = in.read();
        int ch3 = in.read();
        int ch4 = in.read();
        if ((ch1 | ch2 | ch3 | ch4) < 0) {
            throw new EOFException();
        } else {
            return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
        }
    }

    public Object[] createRemoteProxy(ClassLoader loader, ComponentMetaVO metaVO, Address[] address) {
        Object[] remotes = new Object[address.length];

        for(int i = 0; i < address.length; ++i) {
            remotes[i] = RemoteProxyFactory.getDefault().createRemoteProxy(loader, metaVO, new SimpleRemoteAddressSelector(address[i]));
        }

        return remotes;
    }
}