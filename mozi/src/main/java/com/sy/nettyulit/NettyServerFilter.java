package com.sy.nettyulit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
/*
 * 这些是Java NIO中最重要的通道的实现：
    FileChannel
    DatagramChannel
    SocketChannel
    ServerSocketChannel
FileChannel 从文件中读写数据。
DatagramChannel 能通过UDP读写网络中的数据。
SocketChannel 能通过TCP读写网络中的数据。
ServerSocketChannel可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
 */
public class NettyServerFilter extends ChannelInitializer<SocketChannel> {
	//Channel是通道的意思，也就是数据通道，可以运输多种数据结构到对应的服务器端进行对应的交换
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
    
    	System.out.println("用户访问带来的默认数据============="+ch.toString());
        ChannelPipeline ph = ch.pipeline();
        //ph.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        // 以("\n")为结尾分割的 解码器
        // 解码和编码，应和客户端一致
        ph.addLast("decoder", new StringDecoder());
        ph.addLast("encoder", new StringEncoder());
        //处理客户端发送的数据进行交互
        ph.addLast("handler", new NettyServerHandler());// 服务端业务逻辑
    }
}
