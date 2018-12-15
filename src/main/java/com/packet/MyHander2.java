package com.packet;/*
 * @Author Administrator
 * @Description //TODO
 * @Date 2018/12/7/007
 **/

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.UpstreamMessageEvent;

public class MyHander2 extends SimpleChannelHandler {
    private  int count = 1;

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

        String message = (String) e.getMessage();
        System.out.println("hander2 : " + message + " " + count);
        count++;

        }
}
