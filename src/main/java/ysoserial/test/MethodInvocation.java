package ysoserial.test;

import com.google.gson.annotations.Expose;
import java.lang.reflect.Method;
import java.util.List;
import nc.bs.framework.js.servlet.InternalJsInvokeServlet;
import nc.bs.logging.Log;

public class MethodInvocation {
    public static final Log logger = Log.getInstance(InternalJsInvokeServlet.class);
    @Expose
    private String serviceName = null;
    @Expose
    private String methodName = null;
    @Expose
    private String[] parameterTypes = null;
    @Expose
    private String dataSource = null;
    @Expose
    private String userCode = null;
    @Expose
    private String userPwd = null;
    private Method method = null;
    @Expose
    private List<Object> parameters = null;

    public MethodInvocation() {
    }

    public Object invoke(Object implementation) throws Throwable {
        Method method = null;
        method = this.getMethod(implementation.getClass());
        Object result = method.invoke(implementation, this.parameters.toArray());
        return result;
    }

    public Method getMethod(Class<?> intf) throws NoSuchMethodException, ClassNotFoundException {
        if (this.method == null) {
            Class<?>[] pTypes = new Class[this.parameterTypes.length];

            for(int i = 0; i < this.parameterTypes.length; ++i) {
                String className = this.parameterTypes[i];
                if (className.equals("int")) {
                    pTypes[i] = Integer.TYPE;
                } else if (className.equals("double")) {
                    pTypes[i] = Double.TYPE;
                } else if ("float".equals(className)) {
                    pTypes[i] = Float.TYPE;
                } else if ("short".equals(className)) {
                    pTypes[i] = Short.TYPE;
                } else if ("boolean".equals(className)) {
                    pTypes[i] = Boolean.TYPE;
                } else {
                    pTypes[i] = Class.forName(className);
                }
            }

            this.method = intf.getMethod(this.methodName, pTypes);
            if (this.method == null) {
                throw new NoSuchMethodException("Method not found: " + this.methodName);
            }
        }

        return this.method;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<Object> getParameters() {
        return this.parameters;
    }

    public void setParameters(List<Object> parameters) {
        this.parameters = parameters;
    }

    public String[] getParameterTypes() {
        return this.parameterTypes;
    }

    public void setParameterTypes(String[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Method getMethod() {
        if (this.method == null) {
            try {
                this.method = this.getMethod(Class.forName(this.serviceName));
            } catch (Exception var2) {
                logger.error("get method error!", var2);
            }
        }

        return this.method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getDataSource() {
        return this.dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getUserPwd() {
        return this.userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCode() {
        return this.userCode;
    }
}