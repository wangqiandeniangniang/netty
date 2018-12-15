package com.decouple;/*
 * @Author Administrator
 * @Description //其实就是把需要执行的方法收集起来，需要执行统一执行
 * @Date 2018/12/11/011
 **/

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("app.xml");

        Invoker invoker = InvokerHolder.getInvoker((short) 1, (short) 1);
        invoker.invoke(null);
    }
}
