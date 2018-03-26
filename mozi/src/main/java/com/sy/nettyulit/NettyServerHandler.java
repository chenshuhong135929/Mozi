package com.sy.nettyulit;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

import java.util.Date;

import com.sy.pojo.Bluetooth;
import com.sy.pojo.Eqsms;
import com.sy.pojo.Equipment;
import com.sy.pojo.EquipmentData;
import com.sy.pojo.Positionig;
import com.sy.pojo.User;
import com.sy.service.Bluetoothservice;
import com.sy.service.Eqsmsservice;
import com.sy.service.EquipmentDataservice;
import com.sy.service.Equipmentservice;
import com.sy.service.Positionigservice;
import com.sy.service.UserEqservice;
import com.sy.service.Userservice;
import com.sy.service.impl.Bluetoothserviceimpl;
import com.sy.service.impl.Eqsmsserviceimpl;
import com.sy.service.impl.EquipmentDataserviceimpl;
import com.sy.service.impl.Equipmentserviceimpl;
import com.sy.service.impl.Positionigserviceimpl;
import com.sy.service.impl.UserEqserviceimpl;
import com.sy.service.impl.Userserviceimpl;

public class NettyServerHandler extends SimpleChannelInboundHandler<String> {
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		// channel失效，从Map中移除
		NettyChannelMap.remove((SocketChannel) ctx.channel());
		ServleMap.remove((SocketChannel) ctx.channel());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext,
			String msg) throws Exception {

		System.out.println("设备数据=========" + msg);
		
		if (msg.equals("0")) {
			System.out.println("心跳======" + channelHandlerContext.toString());
			// 回复心跳
			channelHandlerContext.writeAndFlush("1");
			//
		} else {
			if(msg.contains("$") &&  !msg.contains("\r\n")){
				msg=	msg.substring(0, msg.length()-4);
				System.out.println("去除标示符后的数据==" + msg);
			Equipmentservice  eservice = new Equipmentserviceimpl();
			EquipmentDataservice eqserive = new EquipmentDataserviceimpl();
			UserEqservice userservice = new UserEqserviceimpl();
			UserEqservice usereqservice= new UserEqserviceimpl();
			String[] texts = msg.split("\\|");
			String instruction = texts[1];
			String imei = texts[0].substring(1, texts[0].length());
			System.out.println("imei==" + imei);
			System.out.println("指令码==" + instruction);
			//将imei通讯通道存储到map里面进行聊天通讯
			 NettyChannelMap.add(imei,(SocketChannel)channelHandlerContext.channel());
			if (instruction.equals("T01")) {
				System.out.println("登陆" + imei);
				Equipment e = eqserive
						.equipmentstatus(texts[3], texts[2], imei);
				channelHandlerContext.writeAndFlush(new Date() + e.toString());
			} else if (instruction.equals("T02")) {
				
				Equipment e=eqserive.getimeiid(imei);
				if(e !=null){
					//使用者的id
					Integer userid =usereqservice.getimei(imei);
					EquipmentData edata = new EquipmentData();
					System.out.println("普通数据上报" + imei);
					String[] hs = texts[2].split(",");
					for (int i = 0; i < hs.length; i++) {
						System.out.println("基本数据==" + hs[i]);
						String h = hs[i].substring(0, 2);
						String text = hs[i].substring(3,  hs[i].length());
						String q = hs[i].substring(0, 1);
						System.out.println("切除的基本数据==" + h);
						if (h.equals("H1")) {
							edata.setHeartrate(Integer.parseInt(text));
						} else if (h.equals("H2")) {
							edata.setHighpressure(text);
						} else if (h.equals("H3")) {
							edata.setBottompressure(text);
						} else if (h.equals("H4")) {
							edata.setQxygen(text);
						} else if (h.equals("H5")) {
							edata.setMocrocirculation(text);
						} else if (h.equals("H6")) {
							edata.setHrv(text);
						} else if (h.equals("H7")) {
							edata.setMood(text);
						} else if (h.equals("H8")) {
							edata.setBreathe(text);
						} else if (h.equals("G1")) {
							edata.setStepWhen(Integer.parseInt(text));
						} else if (h.equals("G2")) {
							edata.setSleeping(text);
						} else if (h.equals("G3")) {
							edata.setCarrieroad(text);
						} else if (h.equals("G4")) {
							edata.setSedentary(text);
						} else if (h.equals("G5")) {
							edata.setCrash(text);
						} else if (h.equals("G6")) {
							edata.setMovementstate(text);
						} else if (h.equals("T1")) {
							edata.setBodytemp(text);
						} else if (h.equals("T2")) {
							edata.setHumidity(text);
						} else if (h.equals("M1")) {
							e.setLordpower(Integer.parseInt(text));
						} else if (h.equals("M2")) {
							e.setsignalxhao(text);
						} else if (h.equals("M3")) {
							edata.setPositioningS(text);
						} else if (h.equals("M4")) {
							edata.setPositioningData(text);
						} else if (q.equals("Q")) {
							e.setBluetoothElectricity(Integer.parseInt(text));
						}
					}
					edata.setUserId(userid);
					boolean stu =eqserive.addEquipmentData(edata);
					if(stu){
						System.out.println("添加设备健康数据成功！！！");
					}else {
						System.out.println("添加设备健康数据失败！！！");
					}
					boolean est =eservice.updateEquipment(e);
					if(est){
						System.out.println("跟新设备基本数据成功！！！");
					}else {
						System.out.println("跟新设备基本数据失败！！！");
					}
				}else {
					System.out.println("T02操作上传的设备不存在");
				}
				
				
			} else if (instruction.equals("T03")) {
				System.out.println("生理数据紧急上报" + imei);
				channelHandlerContext.writeAndFlush("T03|OK\r\n");
				
			} else if (instruction.equals("T04")) {
				System.out.println("设备状态" + imei);
				Equipment e=eqserive.getimeiid(imei);
				String h = texts[2].substring(0, 1);
				if(h.equals("H")){
					String text =texts[2].substring(2, texts[2].length());
					e.setBluetoothStatus(text);
					e.setEqStatus(text);
					e.setUpdatetime(new Date());
					boolean est  = eservice.updateEquipment(e);
					if(est){
						
						channelHandlerContext.writeAndFlush("T04|OK\r\n");
						System.out.println("跟新设备状态成功！！！");
					}else {
						channelHandlerContext.writeAndFlush("T04|ERR\r\n");
						System.out.println("跟新设备状态失败！！！");
					}
				}
				
			} else if (instruction.equals("T05")) {
				Positionigservice positionigservice = new Positionigserviceimpl();
				System.out.println("上报GPS" + imei);
				String[] hs = texts[2].split(",");
				Positionig p = new Positionig();
				for (int i = 0; i < hs.length; i++) {
					String h = hs[i].substring(0, 2);
					String text = hs[i].substring(3,  hs[i].length());
				
					if(h.equals("M3")){
						p.setCratetime(new Date());
						p.setImei(imei);
						p.setPositioningS(text);
						
					}else if(h.equals("M4")){
						p.setCratetime(new Date());
						p.setImei(imei);
						p.setPositioningData(text);
						
					}
				}
				boolean res= positionigservice.addPosition(p);
				if(res){
					System.out.println("上报GPS   成功！！！");
				}else {
					System.out.println("上报GPS   失败！！！");
				}
			} else if (instruction.equals("T06")) {
				String[] hs = texts[2].split(",");
				Eqsmsservice eqsmsservice = new Eqsmsserviceimpl();
				Eqsms s = new Eqsms();
				s.setCratetime(new Date());
				s.setEsms(hs[1]);
				s.setImei(imei);
				s.setPhone(hs[0]);
				eqsmsservice.addEqsms(s);
				System.out.println("上传短信" + imei);
			} else if (instruction.equals("T07")) {
				Bluetoothservice bluetoothservice = new Bluetoothserviceimpl();
				Bluetooth b = bluetoothservice.getBluetoothid(imei);
				if(b !=null){
					b.setBluetoothnd(texts[2]);
					b.setRecentime(new Date());
					b.setImei(imei);
					if(bluetoothservice.updateBluetooth(b)){
						
						channelHandlerContext.writeAndFlush("T07|OK\r\n");
					}else {
						channelHandlerContext.writeAndFlush("T07|ERR\r\n");
					}
				}else {
					b= new Bluetooth();
					b.setBluetoothnd(texts[2]);
					b.setRecentime(new Date());
					b.setCratetime(new Date());
					b.setImei(imei);
					bluetoothservice.addBluetooth(b);
				}
				System.out.println("上传搜索到的蓝牙设备" + imei);
			} else if (instruction.equals("T08")) {
				Bluetoothservice bluetoothservice = new Bluetoothserviceimpl();
				Bluetooth b = bluetoothservice.getBluetoothid(imei);
				if(b !=null){
					b.setImei(imei);
					b.setCurrent(texts[2]);
					b.setRecentime(new Date());
					if(bluetoothservice.updateBluetooth(b)){
						channelHandlerContext.writeAndFlush("$R18|OK\r\n");
					}else {
						channelHandlerContext.writeAndFlush("$R18|ERR\r\n");
					}
				}else {
					b= new Bluetooth();
					b.setImei(imei);
					b.setCurrent(texts[2]);
					b.setRecentime(new Date());
					if(bluetoothservice.addBluetooth(b)){
						channelHandlerContext.writeAndFlush("$R18|OK\r\n");
					}else {
						channelHandlerContext.writeAndFlush("$R18|ERR\r\n");
					}
				}
				System.out.println("蓝牙连接成功后返回" + imei);
			} else if (instruction.equals("T09")) {
				System.out.println("上传通话记录" + imei);
			} else if (instruction.equals("T11")) {
			
				Integer userid =usereqservice.getimei(imei);
				Userservice userserice = new Userserviceimpl();
				User u = userserice.getuserid(userid);
				channelHandlerContext.writeAndFlush("$R03|OK|"+u.getName()+"|ERR\r\n");
				System.out.println("查询用户名昵称" + imei);
			} else if (instruction.equals("T12")) {
				channelHandlerContext.writeAndFlush("$R01|OK|"+new Date()+"|ERR\r\n");
				System.out.println("查询时间" + imei);
			} else if (instruction.equals("T13")) {
				System.out.println("查询天气预报" + imei);
			} else if (instruction.equals("T14")) {
				System.out.println("分析健康数据" + imei);
			} else if (instruction.equals("T21")) {
				System.out.println("查询阈值设定" + imei);
			} else if (instruction.equals("T22")) {
				System.out.println("查询呼入白名单" + imei);
			} else if (instruction.equals("T23")) {
				System.out.println("查询拨号号码设定" + imei);
			}
			}else {
				
			}
		}
		

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
		System.out.println("CLIENT" + getRemoteAddress(ctx) + " 接入连接");
	}

	public static String getRemoteAddress(ChannelHandlerContext ctx) {
		String socketString = "";
		socketString = ctx.channel().remoteAddress().toString();
		return socketString;
	}

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
			stringBuilder.append(' ');
		}
		return stringBuilder.toString();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
		System.out.println("异常信息：\r\n" + cause.getMessage());
	}

}
