//package com.example.netty.helloNetty;
//
//
//import org.jboss.netty.buffer.ChannelBuffers;
//import org.jboss.netty.channel.*;
//
//public class HelloHandler extends SimpleChannelHandler {
//    /**
//     * 接收数据
//     * @param ctx
//     * @param e
//     * @throws Exception
//     */
//    @Override
//    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//        System.out.println("messageReceived");
//        System.out.println(e.getMessage());
//        ctx.getChannel().write("你好啊啊");
//        ctx.getChannel().write(ChannelBuffers.copiedBuffer("你好".getBytes()));
//        super.messageReceived(ctx, e);
//    }
//
//    /**
//     * 捕获异常
//     * @param ctx
//     * @param e
//     * @throws Exception
//     */
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
//        System.out.println("exceptionCaught");
//        super.exceptionCaught(ctx, e);
//    }
//
//    @Override
//    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//        System.out.println("channelConnected");
//        super.channelConnected(ctx, e);
//    }
//
//    /**
//     * 建立成功之后才会断开，关闭通道的时候才会触发
//     * @param ctx
//     * @param e
//     * @throws Exception
//     */
//    @Override
//    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//        System.out.println("channelDisconnected");
//        super.channelDisconnected(ctx, e);
//    }
//
//    /**
//     * 释放资源的， channel 关闭的时候触发
//     * @param ctx
//     * @param e
//     * @throws Exception
//     */
//    @Override
//    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//        System.out.println("channelClosed");
//        super.channelClosed(ctx, e);
//    }
//}
