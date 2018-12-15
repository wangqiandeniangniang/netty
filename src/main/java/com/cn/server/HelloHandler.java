package com.cn.server;


import com.cn.constant.StateCode;
import com.cn.model.Request;
import com.cn.model.Response;
import com.cn.module.fuben.request.FightRequest;
import com.cn.module.fuben.response.FightResponse;
import org.jboss.netty.channel.*;

public class HelloHandler extends SimpleChannelHandler {
    /**
     * 接收数据
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        Request message = (Request) e.getMessage();
        if (message.getModule() == 1) {
            if (message.getCmd() == 1) {
                FightRequest fightRequest = new FightRequest();
                fightRequest.readFromBytes(message.getData());
                System.out.println("fubenId:" + fightRequest.getFubenId()
                    + "   count: " + fightRequest.getCount());

                FightResponse fightResponse = new FightResponse();
                fightResponse.setGold(9999);

                Response response = new Response();
                response.setModule((short)1);
                response.setCmd((short)1);
                response.setStateCode(StateCode.SUCCESS);
                response.setData(fightResponse.getBytes());
                ctx.getChannel().write(response);
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
