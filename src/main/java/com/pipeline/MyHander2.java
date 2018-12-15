package com.pipeline;/*
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


    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
        byte[] array = buffer.array();
        String message = new String(array);
        System.out.println("hander1 : " + message + " ");

        //传递
        ctx.sendUpstream(new UpstreamMessageEvent(ctx.getChannel(), message,e.getRemoteAddress()));
        //传递
        ctx.sendUpstream(new UpstreamMessageEvent(ctx.getChannel(), "ddfdfdf",e.getRemoteAddress()));
    }
}
