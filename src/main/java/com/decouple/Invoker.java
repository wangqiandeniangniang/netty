package com.decouple;/*
 * @Author Administrator
 * @Description //TODO
 * @Date 2018/12/11/011
 **/

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invoker  {

    /**
     * 目标对象
     */
    private Object target;


    /**
     * 方法
     */
    private Method method;

    public static Invoker valueOf(Object object, Method method) {
        Invoker invoker = new Invoker();
        invoker.setTarget(object);
        invoker.setMethod(method);
        return invoker;
    }

    /**
     * 执行器
     * @param args
     * @return
     */
    public  Object invoke(Object[] args) {
        try {
            method.invoke(target, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
