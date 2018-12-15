package com.serialself.core;/*
 * @Author Administrator
 * @Description //TODO
 * @Date 2018/12/1/001
 **/

import com.serialself.Player;

import java.util.Arrays;

public class Test4 {
    public static void main(String[] args) {
        Player player = new Player();
        player.setPlayerId(1001);
        player.setAge(20);
        player.getSkills().add(101);
        byte[] bytes = player.getBytes();
        System.out.println(Arrays.toString(bytes));
 //==================================================
        Player player2 = new Player();
        player2.readFromBytes(bytes);
        System.out.println(player2.getPlayerId());
        System.out.println(player2.getAge());
        System.out.println(Arrays.toString(player2.getSkills().toArray()));
    }
}
