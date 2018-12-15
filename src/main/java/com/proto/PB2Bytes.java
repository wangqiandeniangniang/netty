package com.proto;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

/**
 *  protobuf学习
 */
public class PB2Bytes {

    public static void main(String[] args) throws Exception {
        byte[] bytes = toBytes();
        toPlayer(bytes);

    }

    public static  byte[] toBytes() {
        //获取一个PBPlayer的构造器
        PlayerModule.PBPlayer.Builder builder = PlayerModule.PBPlayer.newBuilder();

        //设置数据
        builder.setPlayId(1000).setAge(20).setName("peter").addSkills(1001);
        //构造出对象
        PlayerModule.PBPlayer player = builder.build();

        //序列化字节数组
        byte[] byteArray = player.toByteArray();
        System.out.println(Arrays.toString(byteArray));
        return byteArray;

    }

    /**
     * 反序列
     */
    public static void toPlayer(byte[] bs) throws Exception {
        PlayerModule.PBPlayer player = PlayerModule.PBPlayer.parseFrom(bs);
        System.out.println("playerId:" + player.getPlayId());
        System.out.println("age:" + player.getAge());
        System.out.println("name: " + player.getName());
        System.out.println("skills " + Arrays.toString(player.getSkillsList().toArray()));
    }
}
