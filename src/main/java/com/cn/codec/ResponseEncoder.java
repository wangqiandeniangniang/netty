package com.cn.codec;/*
 * @Author Administrator
 * @Description响应编码对象
 * @Date 2018/12/5/005
 *
 * 响应编码器
 * <pre>
 * 数据包格式
 * 包头 | 模块号 | 命令号 | 状态码 | 长度 | 数据
 * </pre>
 * 包头 4字节
 * 模块号2字节short
 * 命令号2字节short
 * 长度4个字节 （描述数据的长度）
 **/

import com.cn.constant.ConstantValue;
import com.cn.model.Request;
import com.cn.model.Response;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

public class ResponseEncoder extends OneToOneEncoder {

    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
        Response request = (Response) msg;

        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        //包头
        buffer.writeInt(ConstantValue.FLAG);
        // module
        buffer.writeShort(request.getModule());
        //cmd
        buffer.writeShort(request.getCmd());
        //状态码
        buffer.writeInt(request.getStateCode());
        //data 长度
        buffer.writeInt(request.getDataLength());
        //data 内容
        if (request.getData() != null) {
            buffer.writeBytes(request.getData());
        }


        return buffer;
    }
}
