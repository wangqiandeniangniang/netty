package com.example.netty.netty5;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多连接客户端
 */
public class MultiClient {
    /**
     * 服务类
     */
    private Bootstrap bootstrap = new Bootstrap();


    /**
     * 会话
     */
    private List<Channel> channels = new ArrayList<>();

    /**
     * 引用计数
     */
    private final AtomicInteger index = new AtomicInteger();

    /**
     * 初始化
     * @param count
     */
    public void init(int count) {
        // worker
        EventLoopGroup worker = new NioEventLoopGroup();

        //设置线程池
        bootstrap.group(worker);

        //设置socket工厂
        bootstrap.channel(NioSocketChannel.class);

        //设置管道
        bootstrap.handler(new ChannelInitializer<io.netty.channel.Channel>() {
            @Override
            protected void initChannel(io.netty.channel.Channel channel) throws Exception {
                channel.pipeline().addLast(new StringDecoder());
                channel.pipeline().addLast(new StringEncoder());
                channel.pipeline().addLast(new ClientHandler());
            }
        });

        for (int i = 1; i <= count; i++) {
            ChannelFuture connect = bootstrap.connect("127.0.0.1", 10101);
            channels.add(connect.channel());
        }
    }

    /**
     * 获取会话
     */
    public Channel nextChannel() {
        return getFirstActiveChannel(0);
    }

    private Channel getFirstActiveChannel(int count) {
        Channel channel = channels.get(Math.abs(index.getAndIncrement() % channels.size()));
        if (!channel.isActive()) {
            //重连
            reconnect(channel);
            if (count >= channels.size()) {
                throw new RuntimeException("没有线程可用");
            }
            return getFirstActiveChannel(count + 1);
        }
        return channel;
    }

    /**
     * 重连
     */

    private void reconnect(Channel channel) {
        synchronized (channel) {
            if (channels.indexOf(channel) == -1) {
                return;
            }
            Channel newChannel = bootstrap.connect("127.0.0.1", 10101).channel();
            channels.set(channels.indexOf(channel), newChannel);
        }
    }
}
