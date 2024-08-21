package com.example.demo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("Received message from server: " + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 连接建立后，发送数据给服务器
        String message = "Hello from Client!";
        ctx.writeAndFlush(message);  // 通过 ctx 发送数据
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close(); // 出现异常时关闭连接
    }

}
