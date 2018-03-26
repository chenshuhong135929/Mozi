package com.sy.nettyulit;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SocketClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	System.out.println("获取到的数据+SocketClientHandler"+ctx+"文本"+msg);
        Channel channel = ctx.channel();

        ByteBuf responseBuf = (ByteBuf)msg;
        responseBuf.markReaderIndex();

        int length = responseBuf.readInt();
        
        int seq = responseBuf.readInt();

        responseBuf.resetReaderIndex();

        //获取消息对应的callback
        SocketClient.CallbackService callbackService = ChannelUtils.<SocketClient.CallbackService>removeCallback(channel, seq);
        callbackService.receiveMessage(responseBuf);
    }
}
