package com.wzz.dubborpc.customer;

import com.wzz.dubborpc.netty.NettyClient;
import com.wzz.dubborpc.publicinterface.HelloService;

public class ClientBootstrap {
    //协议头
    public static final String providerName = "HelloService#hello#";

    public static void main(String[] args) {
        //创建消费者
        NettyClient customer = new NettyClient();
        //创建代理
        HelloService service = (HelloService) customer.getBean(HelloService.class,providerName);
        //通过代理对象调用服务提供者的方法（服务）
        String res = service.hello("hello dubbo");
        System.out.println("调用结构res="+res);

    }
}
