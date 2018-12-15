package com.decouple;/*
 * @Author Administrator
 * @Description //执行器管理器
 * @Date 2018/12/11/011
 **/

import java.util.HashMap;
import java.util.Map;

public class InvokerHolder {

    public static Map<Short, Map<Short, Invoker>> invokers = new HashMap<>();


    /**
     * 添加执行器
     * @param module
     * @param cmd
     * @param invoker
     */
    public static void addInvoker(short module, short cmd, Invoker invoker) {
        Map<Short, Invoker> map = invokers.get(module);
        if (map == null) {
            map = new HashMap<>();
            invokers.put(module, map);
        }
        map.put(cmd, invoker);

    }

    /**
     * 获取执行器
     * @param module
     * @param cmd
     * @return
     */
    public static Invoker getInvoker(short module, short cmd) {
        Map<Short, Invoker> map = invokers.get(module);
        if (map != null) {
            return map.get(cmd);
        }
        return null;
    }
}
