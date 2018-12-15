package com.pipeline;


import com.cn.codec.RequestDecoder;
import com.cn.codec.ResponseEncoder;
import com.cn.server.HelloHandler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {

        //服务类
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        //设置nioSocket 工厂
        serverBootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));

        //设置管道的工厂
        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {

                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("hander2", new MyHander2());
                pipeline.addLast("hander1", new MyHander1());

                return pipeline;
            }
        });
        serverBootstrap.bind(new InetSocketAddress(10101));
        System.out.println("Start!!!!");
    }
}
