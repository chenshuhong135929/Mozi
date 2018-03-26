package com.sy.nettyulit;

import java.util.Map;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import net.sf.json.JSONObject;

public class NettyClientHandler extends SimpleChannelInboundHandler<BaseMsg> {
	//利用写空闲发送心跳检测消息
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case WRITER_IDLE:
                    PingMsg pingMsg=new PingMsg();
                    ctx.writeAndFlush(pingMsg);
                    System.out.println("send ping to server-");
                    break;
                default:
                    break;
            }
        }
    }
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg) throws Exception {
        MsgType msgType=baseMsg.getType();
        switch (msgType){
            case LOGIN:{
                //向服务器发起登录
                LoginMsg loginMsg=new LoginMsg();
                loginMsg.setPassword("yao");
                loginMsg.setUserName("robin");
                channelHandlerContext.writeAndFlush(loginMsg);
            }break;
            case PING:{
                System.out.println("从服务端接收到心跳数据=============receive ping from server-");
            }break;
            
            //回复服务器端
            case ASK:{
                ReplyClientBody replyClientBody=new ReplyClientBody("我是客户==================");
                ReplyMsg replyMsg=new ReplyMsg();
                replyMsg.setBody(replyClientBody);
                channelHandlerContext.writeAndFlush(replyMsg);
            }break;
          //服务端的提问数据（也就是回复内容）
            case REPLY:{
                ReplyMsg replyMsg=(ReplyMsg)baseMsg;
                ReplyServerBody replyServerBody=(ReplyServerBody)replyMsg.getBody();
                try {
                	JSONObject js = new JSONObject().fromObject(replyServerBody.getServerInfo());
                	NettyChannelMap.map  = (Map<String, SocketChannel>) js.get("map");
                	NettyChannelMap.Traverse();
				} catch (Exception e) {
					 System.out.println("服务器端说： ============receive client msg: "+replyServerBody.getServerInfo());
				}
              
               
            }
            default:break;
        }
        ReferenceCountUtil.release(msgType);
    }
    
  
}
