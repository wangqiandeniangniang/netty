package com.decouple;/*
 * @Author Administrator
 * @Description //TODO
 * @Date 2018/12/11/011
 **/

import com.decouple.annotation.SocketCmd;
import com.decouple.annotation.SocketModule;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class Scanner implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces != null && interfaces.length > 0) {
            //扫描所有接口
            for (Class<?> interFace : interfaces) {
                SocketModule socketModule= interFace.getAnnotation(SocketModule.class);
                if (socketModule == null) {
                    continue;
                }
                Method[] methods = interFace.getMethods();
                if (methods != null && methods.length > 0) {
                    for (Method method : methods) {
                        SocketCmd socketCmd = method.getAnnotation(SocketCmd.class);

                        if (socketCmd == null) {
                            continue;
                        }
                        short module = socketModule.module();
                        short cmd = socketCmd.cmd();
                        Invoker invoker = Invoker.valueOf(bean, method);
                        if (InvokerHolder.getInvoker(module, cmd) == null) {
                            InvokerHolder.addInvoker(module,cmd,invoker);
                        } else {
                            System.out.println("重复注册执行器module : " + module + " cmd: " + cmd);
                        }
                    }
                }
            }
        }
        return bean;
    }
}
