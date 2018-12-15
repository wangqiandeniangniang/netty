package com.cn.client;

import com.cn.model.Response;
import com.cn.module.fuben.response.FightResponse;
import org.jboss.netty.channel.*;



public class HiHandler extends SimpleChannelHandler {
    /**
     * 接收数据
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        Response message = (Response) e.getMessage();
        if (message.getModule() == 1) {
            if (message.getCmd() == 1) {
                FightResponse fightResponse = new FightResponse();
                fightResponse.readFromBytes(message.getData());
                System.out.println("获取金币为：" + fightResponse.getGold());

            } else if (message.getCmd() == 2) {

            }
        } else if (message.getModule() == 2) {

        }
    }

    /**
     * 捕获异常
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("exceptionCaught");
        super.exceptionCaught(ctx, e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelConnected");
        super.channelConnected(ctx, e);
    }

    /**
     * 建立成功之后才会断开，关闭通道的时候才会触发
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    /**
     * 释放资源的， channel 关闭的时候触发
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        super.channelClosed(ctx, e);
    }
}
