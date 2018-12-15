package com.cn.module.fuben.request;/*
 * @Author Administrator
 * @Description //TODO
 * @Date 2018/12/6/006
 **/

import com.cn.serial.Serializer;

public class FightRequest extends Serializer {

    /**
     * 副本ID
     */
    private int fubenId;

    /**
     * 次数
     */
    private int count;

    public int getFubenId() {
        return fubenId;
    }

    public void setFubenId(int fubenId) {
        this.fubenId = fubenId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    protected void read() {
        this.fubenId = readInt();
        this.count = readInt();
    }

    @Override
    protected void write() {
        writeInt(fubenId);
        writeInt(count);
    }
}
