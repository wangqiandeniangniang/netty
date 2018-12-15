package com.proto.java;/*
 * @Author Administrator
 * @Description //TODO
 * @Date 2018/11/30/030
 **/

import java.io.*;
import java.util.Arrays;

public class JAVA2Bytes  {

    public static void main(String[] args) throws Exception {
        byte[] bytes = toBytes();
        toPlayer(bytes);
    }

    /**
     * 序列化
     */
    public static byte[] toBytes() throws IOException {
        Player player = new Player(101, 20, "peter");
        player.getSkills().add(1001);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        //写入对象
        objectOutputStream.writeObject(player);

        //获取字节数组
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(byteArray));
        return byteArray;
    }

    /**
     * 反序列化
     */

    public static void toPlayer(byte[] bs) throws  Exception {

        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bs));
        Player player = (Player)objectInputStream.readObject();
        System.out.println("playerId:" + player.getPlayerId());
        System.out.println("age:" + player.getAge());
        System.out.println("name: " + player.getName());
        System.out.println("skills " + Arrays.toString(player.getSkills().toArray()));
    }
}
