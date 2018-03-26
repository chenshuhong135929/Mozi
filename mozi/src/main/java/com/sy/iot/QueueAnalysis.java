package com.sy.iot;

import com.alibaba.fastjson.JSON;
import com.aliyun.mns.model.Message;
import com.sy.iot.utils.DeviceStatus;

import com.sy.iot.utils.IotMsg;

//import com.sy.hotel.service.DeviceService;

import com.sy.utils.Base64Util;
import com.sy.utils.SpringContextUtil;

/**
 * 队列消息解析，用户于解析 上传过来的数据
 * 
 * @author Administrator
 *
 */
public class QueueAnalysis {

	/**
	 * 解析数据
	 * 
	 * @param message
	 */
	public static void analysis(Message message) {

		// 解析原始数据
		IotMsg msg = JSON.parseObject(message.getMessageBody(), IotMsg.class);

		// 先测试 输出
		System.out.println("\n" + Base64Util.getFromBASE64(msg.getPayload()) + "\n");

		if ("status".equals(msg.getMessagetype())) {
			// 设备状态变化
			// 解析成设备状态对象
			DeviceStatus sta = JSON.parseObject(Base64Util.getFromBASE64(msg.getPayload()), DeviceStatus.class);
			// 调用处理方法
			System.out.println("更新设备状态：设备名称：" + sta.getDeviceName() + "  状态：" + sta.getStatus());

		} else if ("upload".equals(msg.getMessagetype())) {
			// 设备 上报数据
			// 产品Key
			String productKey = msg.getTopic().substring(msg.getTopic().indexOf("/") > 0 ? 1 : 0);
			productKey = productKey.substring(0, productKey.indexOf("/"));
			// 设备名
			String deviceName = "";
			deviceName = msg.getTopic().substring(0, msg.getTopic().lastIndexOf("/"));
			deviceName = deviceName.substring(deviceName.lastIndexOf("/") + 1);
			// 上传的数据
			String messageCon = Base64Util.getFromBASE64(msg.getPayload());
			// 调用处理方法

		}

	}

}
