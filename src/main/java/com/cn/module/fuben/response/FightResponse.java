package com.cn.module.fuben.response;/*
 * @Author Administrator
 * @Description //TODO
 * @Date 2018/12/6/006
 **/

import com.cn.serial.Serializer;

public class FightResponse extends Serializer {


    /**
     * 获取金币
     */
    private int gold;

    @Override
    protected void read() {
        this.gold = readInt();
    }

    @Override
    protected void write() {
        writeInt(gold);
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
