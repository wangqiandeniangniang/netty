package com.pipeline;/*
 * @Author Administrator
 * @Description //TODO
 * @Date 2018/12/7/007
 **/

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class MyHander1 extends SimpleChannelHandler {


    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        String message = (String) e.getMessage();

        System.out.println("handler2: " + message);
    }
}
