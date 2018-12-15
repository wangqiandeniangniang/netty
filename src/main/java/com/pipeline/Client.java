package com.pipeline;/*
 * @Author Administrator
 * @Description //TODO
 * @Date 2018/12/7/007
 **/

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10101);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello".getBytes());
        socket.close();
    }
}
