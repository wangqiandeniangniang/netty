package com.serialself;/*
 * @Author Administrator
 * @Description 利用ByteBuffer实现序列化， 容量不可扩容
 * @Date 2018/11/30/030
 **/

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        int id = 101;
        int age = 21;
        ByteBuffer buffer = ByteBuffer.allocate(8); //不会扩容
        buffer.putInt(id);
        buffer.putInt(age);
        byte[] array = buffer.array();
        System.out.println(Arrays.toString(buffer.array()));

        //===============反序列化======================

        ByteBuffer buffer2 = ByteBuffer.wrap(array);
        System.out.println("id:" + buffer2.getInt());
        System.out.println("age: " + buffer2.getInt());

    }
}
