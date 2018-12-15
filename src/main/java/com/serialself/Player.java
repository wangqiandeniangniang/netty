package com.serialself;/*
 * @Author Administrator
 * @Description //TODO
 * @Date 2018/12/1/001
 **/

import com.serialself.core.Serializer;

import java.util.ArrayList;
import java.util.List;

public class Player extends Serializer {

    private long playerId;

    private int age;

    private List<Integer> skills = new ArrayList<>();

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Integer> getSkills() {
        return skills;
    }

    public void setSkills(List<Integer> skills) {
        this.skills = skills;
    }

    @Override
    protected void read() {
        this.playerId = readLong();
        this.age = readInt();
        this.skills = readList(Integer.class);
    }

    @Override
    protected void write() {
        writeLong(playerId);
        writeInt(age);
        writeList(skills);
    }
}
