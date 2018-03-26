package com.sy.nettyulit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

/**
 * 储存硬件端的
 * @author Administrator
 *
 */
public class NettyChannelMap {
	    public static Map<String,SocketChannel> map=new ConcurrentHashMap<String, SocketChannel>();
	    public static void add(String clientId,SocketChannel socketChannel){
	        map.put(clientId,socketChannel);
	    }
	    public static Channel get(String clientId){
	       return map.get(clientId);
	    }
	    public static void remove(SocketChannel socketChannel){
	        for (Map.Entry entry:map.entrySet()){
	            if (entry.getValue()==socketChannel){
	                map.remove(entry.getKey());
	            }
	        }
	    }
	    
	    public static void Traverse(){
	        for (Map.Entry entry:map.entrySet()){
	            System.out.println("value======="+entry.getValue()+" key  =====  "+entry.getKey());
	          //  SocketChannel channel=(SocketChannel) entry.getValue();
	        }
	    }
}
