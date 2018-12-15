package com.example.netty.netty5;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class Server {

    public static void main(String[] args) {
        //服务类
        ServerBootstrap bootstrap = new ServerBootstrap();

        //boss 和 worker
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();



        try {
            //设置线程池
            bootstrap.group(boss, worker);

            //设置socket 工厂
            bootstrap.channel(NioServerSocketChannel.class);

            //设置管道工厂
            bootstrap.childHandler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast(new StringDecoder());
                    channel.pipeline().addLast(new StringEncoder());
                    channel.pipeline().addLast(new ServerHandler());
                }
            });

            //设置参数
            bootstrap.option(ChannelOption.SO_BACKLOG, 2048); //ServerSocketChannel的设置，链接缓冲池的大小
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true); //socketChannel的设置，维持链接的活跃
            bootstrap.childOption(ChannelOption.TCP_NODELAY, true);//socketchannel的设置，关闭延迟发送
            //绑定端口
            ChannelFuture future = bootstrap.bind(10101);

            System.out.println("start");
            //等待服务端关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}
