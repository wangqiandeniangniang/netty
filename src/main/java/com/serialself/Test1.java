package com.serialself;/*
 * @Author Administrator
 * @Description //TODO
 * @Date 2018/11/30/030
 **/

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 原始序列化
 */
public class Test1 {
    public static void main(String[] args) throws IOException {
        int id = 101;

        int age = 21;
        ByteArrayOutputStream arrayOutputStream =  new ByteArrayOutputStream();
        arrayOutputStream.write(int2Bytes(id));
        arrayOutputStream.write(int2Bytes(age));
        byte[] byteArray = arrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(byteArray));

        //=========================================================
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        byte[] idBytes = new byte[4];
        int read = byteArrayInputStream.read(idBytes);
        System.out.println("id:" + bytes2int(idBytes));

        byte[] ageBytes = new byte[4];

        int read1 = byteArrayInputStream.read(ageBytes);
        System.out.println("age:" + bytes2int(ageBytes));

    }


    public static byte[] int2Bytes(int i) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte)(i >> 3*8);
        bytes[1] = (byte)(i >> 2*8);
        bytes[2] = (byte)(i >> 1*8);
        bytes[3] = (byte)(i >> 0*8);
        return bytes;
    }

    /**
     * 大端模式
     */

    public static int bytes2int(byte[] bytes) {

        return ((bytes[0] << 3*8)|
                (bytes[1] << 2*8)|
                (bytes[2] << 1*8)|
                (bytes[3] << 0*8));
    }

}


