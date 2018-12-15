package com.packet;/*
 * @Author Administrator
 * @Description //TODO
 * @Date 2018/12/7/007
 **/

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10101);
        String message = "hello";
        byte[] bytes = message.getBytes();
        ByteBuffer bu = ByteBuffer.allocate(4 + bytes.length);
        bu.putInt(bytes.length);
        bu.put(bytes);
        byte[] array = bu.array();
        OutputStream outputStream = socket.getOutputStream();
        for (int i = 0; i < 1000; i++) {
            outputStream.write(array);
        }
        socket.close();
    }
}
