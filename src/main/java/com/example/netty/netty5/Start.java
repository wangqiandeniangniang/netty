package com.example.netty.netty5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Start {
    public static void main(String[] args) {
        MultiClient multiClient = new MultiClient();
        multiClient.init(5);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("请输入：");
                String readLine = bufferedReader.readLine();
                multiClient.nextChannel().writeAndFlush(readLine);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
