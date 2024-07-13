package io.github.chenyilei2016.gateway.core.session;

import io.github.chenyilei2016.gateway.core.generic.GenericReferenceProxy;
import io.github.chenyilei2016.gateway.core.generic.IGenericReference;
import net.sf.cglib.core.ReflectUtils;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.Type;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

public class CglibTest {
    public interface MyI {

        String $invoke(String args);

    }

    /**
     * public final boolean io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.equals(java.lang.Object)
     * public final java.lang.String io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.toString()
     * public final int io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.hashCode()
     * protected final java.lang.Object io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.clone() throws java.lang.CloneNotSupportedException
     * public java.lang.Object io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.newInstance(net.sf.cglib.proxy.Callback)
     * public java.lang.Object io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.newInstance(java.lang.Class[],java.lang.Object[],net.sf.cglib.proxy.Callback[])
     * public java.lang.Object io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.newInstance(net.sf.cglib.proxy.Callback[])
     * public void io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.setCallback(int,net.sf.cglib.proxy.Callback)
     * public final java.lang.String io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.测试生成(java.lang.String)
     * public static void io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.CGLIB$SET_STATIC_CALLBACKS(net.sf.cglib.proxy.Callback[])
     * public static void io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.CGLIB$SET_THREAD_CALLBACKS(net.sf.cglib.proxy.Callback[])
     * public final java.lang.String io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.$invoke(java.lang.String)
     * public net.sf.cglib.proxy.Callback[] io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.getCallbacks()
     * public net.sf.cglib.proxy.Callback io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.getCallback(int)
     * public static net.sf.cglib.proxy.MethodProxy io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.CGLIB$findMethodProxy(net.sf.cglib.core.Signature)
     * static void io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.CGLIB$STATICHOOK1()
     * private static final void io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.CGLIB$BIND_CALLBACKS(java.lang.Object)
     * final java.lang.String io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.CGLIB$测试生成$5(java.lang.String)
     * final int io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.CGLIB$hashCode$2()
     * final boolean io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.CGLIB$equals$0(java.lang.Object)
     * final java.lang.String io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.CGLIB$$invoke$4(java.lang.String)
     * final java.lang.Object io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.CGLIB$clone$3() throws java.lang.CloneNotSupportedException
     * final java.lang.String io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.CGLIB$toString$1()
     * public void io.github.chenyilei2016.gateway.core.generic.IGenericReference$$EnhancerByCGLIB$$5ce28a0a.setCallbacks(net.sf.cglib.proxy.Callback[])
     */
    @Test
    public void test1(){

        // 创建接口
        InterfaceMaker interfaceMaker = new InterfaceMaker();
        interfaceMaker.add(new Signature("测试生成", Type.getType(String.class), new Type[]{Type.getType(String.class)}), null);
        Class<?> interfaceClass = interfaceMaker.create();
        // 代理对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Object.class);
        // IGenericReference 统一泛化调用接口
        // interfaceClass    根据泛化调用注册信息创建的接口，建立 http -> rpc 关联
        enhancer.setInterfaces(new Class[]{IGenericReference.class, interfaceClass});
        enhancer.setCallback(new MethodInterceptor(){

            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return null;
            }
        });
        Object o = enhancer.create();
        Class aClass = enhancer.createClass();

        Arrays.stream(aClass.getDeclaredMethods()).forEach(method -> {
            //不是object的打印一下
            if (method.getDeclaringClass().isAssignableFrom(Object.class)) {
                return;
            }
            System.err.println(method);
        });
    }
}
