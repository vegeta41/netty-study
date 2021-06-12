package com.wzz.dubborpc.provider;

import com.wzz.dubborpc.netty.NettyServer;

//启动一个服务提供者 就是nettyserver
public class ServerBootstrap {
    public static void main(String[] args) {
        //TODO
        NettyServer.startServer("127.0.0.1", 7000);
    }
}
