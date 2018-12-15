package com.cn.codec;/*
 * @Author Administrator
 * @Description
 * @Date 2018/12/5/005
 * 响应解码器
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
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class ResponseDecoder extends FrameDecoder {
    /**
     * 数据包基本长度
     */
    public static int  BASE_LENGTH = 4 + 2 + 2 + 4 + 4;

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {

        //可读长度大于基本长度
        if (buffer.readableBytes() >= BASE_LENGTH) {
            //记录包头开始的index
            int beginReader = buffer.readerIndex();
            while (true) {
                if (buffer.readableBytes() >= BASE_LENGTH) {
                    if (buffer.readInt() == ConstantValue.FLAG) {
                        break;
                    }
                }
            }
            //模块号
            short module = buffer.readShort();

            //命令号
            short cmd = buffer.readShort();

            //状态码
            int stateCode = buffer.readInt();
            //长度
            int length = buffer.readInt();

            //判断请求数据包数据是否到齐
            if (buffer.readableBytes() < length) {
                //还原读指针
                buffer.readerIndex(beginReader);
                return null;
            }
            //读取数据
            byte[] data = new byte[length];
            buffer.readBytes(data);
            Response response = new Response();
            response.setModule(module);
            response.setCmd(cmd);
            response.setStateCode(stateCode);
            response.setData(data);

            //继续往下传递 sendUpStreamEvent
            return response;
        }

        //数据包不完整，需要等待后面的包来
        return null;

    }
}
