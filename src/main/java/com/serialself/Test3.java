package com.serialself;/*
 * @Author Administrator
 * @Description netty channelBuffer可以自动扩容
 * @Date 2018/11/30/030
 **/

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import java.util.Arrays;

public class Test3 {
    public static void main(String[] args) {

        ChannelBuffer channelBuffer = ChannelBuffers.dynamicBuffer();
        channelBuffer.writeInt(1001);
        channelBuffer.writeDouble(80.1);

        byte[] bytes = new byte[channelBuffer.writerIndex()];
        channelBuffer.readBytes(bytes);
        System.out.println(Arrays.toString(bytes));

        //========================================
        ChannelBuffer wrappedBuffer = ChannelBuffers.wrappedBuffer(bytes);
        System.out.println(wrappedBuffer.readInt());
        System.out.println(wrappedBuffer.readDouble());

    }
}
