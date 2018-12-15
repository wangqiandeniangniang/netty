package com.decouple.service;/*
 * @Author Administrator
 * @Description //TODO
 * @Date 2018/12/11/011
 **/

import com.decouple.annotation.SocketCmd;
import com.decouple.annotation.SocketModule;

/**
 * 用户模块
 */
@SocketModule(module = 1)
public interface UserService {

    /**
     * 登录
     */
    @SocketCmd(cmd = 1)
    public void login();


    /**
     * 获取信息
     */
    @SocketCmd(cmd = 2)
    public void getInfo();

}
