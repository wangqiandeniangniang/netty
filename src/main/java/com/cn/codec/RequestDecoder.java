package com.cn.codec;/*
 * @Author Administrator
 * @Description
 * @Date 2018/12/5/005
 * 请求解码器
 * <pre>
 * 数据包格式
 * 包头 | 模块号 | 命令号 | 长度 | 数据
 * </pre>
 * 包头 4字节
 * 模块号2字节short
 * 命令号2字节short
 * 长度4个字节 （描述数据的长度）
 **/

import com.cn.constant.ConstantValue;
import com.cn.model.Request;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class RequestDecoder extends FrameDecoder {
    /**
     * 数据包基本长度
     */
    public static int  BASE_LENGTH = 4 + 2 + 2 + 4;

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {

        //可读长度大于基本长度
        if (buffer.readableBytes() >= BASE_LENGTH) {
            //防止socket 字节流攻击
            if (buffer.readableBytes() > 2048) {
                buffer.skipBytes(buffer.readableBytes());
            }
            //记录包头开始的index
            int beginReader;
            while (true) {
               beginReader = buffer.readerIndex(); //读取当前索引位置
                buffer.markReaderIndex(); //标记当前位置
                if (buffer.readableBytes() >= BASE_LENGTH) {
                    if (buffer.readInt() == ConstantValue.FLAG) {
                        break;
                    }
                }
                //为读到包头，略过一个字节
                buffer.resetReaderIndex(); //恢复当前位置
                buffer.readByte(); //略过一个字节

                //长度变的不够
                if (buffer.readableBytes() < BASE_LENGTH) {
                    return null;
                }
            }
            //模块号
            short module = buffer.readShort();

            //命令号
            short cmd = buffer.readShort();

            //长度
            int length = buffer.readInt();

            if (buffer.readableBytes() < length) {
                //还原读指针
                buffer.readerIndex(beginReader);
                return null;
            }

            byte[] data = new byte[length];
            buffer.readBytes(data);
            Request request = new Request();
            request.setModule(module);
            request.setCmd(cmd);
            request.setData(data);

            //继续往下传递 sendUpStreamEvent
            return request;
        }

        //数据包不完整，需要等待后面的包来
        return null;

    }
}
