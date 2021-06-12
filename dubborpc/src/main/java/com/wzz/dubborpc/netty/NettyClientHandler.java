package com.wzz.dubborpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {
    private ChannelHandlerContext context;
    private String result;//返回结果
    private String para;//客户端调用传入参数


    //与服务器连接创建后，就会被调用[1]
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(" channelActive 被调用  ");
        //因为在其他方法会使用到ctx
        context = ctx;
    }
    //收到服务器的数据后 调用方法[4]
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(" channelRead 被调用  ");
        result = msg.toString();
        //唤醒等待的线程
        notify();
    }
    //被代理对象调用
    //发送数据给服务器 -> wait(3) -> ChannelRead收到数据(4) -> notify -> 返回结果(5)
    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(para);
        //等待
        wait();
        return result;//服务器返回的结果
    }
    //[2]
    void setPara(String para) {
        System.out.println(" setPara  ");
        this.para = para;
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }


}
