package com.sy.iot;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20170420.RRpcRequest;
import com.aliyuncs.iot.model.v20170420.RRpcResponse;
import com.aliyuncs.iot.model.v20170620.ApplyDeviceWithNamesRequest;
import com.aliyuncs.iot.model.v20170620.ApplyDeviceWithNamesResponse;
import com.aliyuncs.iot.model.v20170620.PubBroadcastRequest;
import com.aliyuncs.iot.model.v20170620.PubBroadcastResponse;
import com.aliyuncs.iot.model.v20170620.PubRequest;
import com.aliyuncs.iot.model.v20170620.PubResponse;
import com.aliyuncs.iot.model.v20170620.RegistDeviceRequest;
import com.aliyuncs.iot.model.v20170620.RegistDeviceResponse;
import com.aliyuncs.iot.model.v20170620.QueryApplyStatusRequest;
import com.aliyuncs.iot.model.v20170620.QueryApplyStatusResponse;
import com.aliyuncs.iot.model.v20170620.QueryPageByApplyIdRequest;
import com.aliyuncs.iot.model.v20170620.QueryPageByApplyIdResponse;
import com.aliyuncs.iot.model.v20170620.QueryPageByApplyIdResponse.ApplyDeviceInfo;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.sy.utils.Config;
import com.sy.iot.utils.SendResultData;
import com.sy.utils.Base64Util;

public class DeviceUtil {
	private static String accessKey; // 访问键
	private static String accessSecret; // 访问密钥

	private static String profileStr; // 节点

	private static Config config = null; // 配置对像

	private static IClientProfile profile = null; // 节点对象
	private static DefaultAcsClient client = null; // SDK 客户端
	// 日志对象
	private static final Logger logger = LoggerFactory.getLogger(DeviceUtil.class);
	
	private DeviceUtil(){}
	
	static {
		config = new Config("iotConfig.properties");
		accessKey = config.getString("accessKey").trim();
		accessSecret = config.getString("accessSecret").trim();
		profileStr = config.getString("profile").trim();

		try {
			DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
			profile = DefaultProfile.getProfile(profileStr, accessKey, accessSecret); // 初始化
																						// 节点

			client = new DefaultAcsClient(profile); // 初始化SDK客户端
		} catch (ClientException e) {
			e.printStackTrace();
			logger.info("初始化 SDK 客户端异常", e);
		}
	}

	/**
	 * 判断返回码
	 */
	public static String returnCode(String code){
		
		if(null == code){
			return null;
		}
		
		switch (code.trim().toLowerCase()) {
		case "success":
			return "成功";
		case "unknow":
			return "系统异常";
		case "timeout":
			return "设备响应超时";
		case "offline":
			return "设备离线";
		case "halfconn":
			return "设备离线";
		}
		return null;
	}
	
	/**
	 * 注册设备
	 * @title null
	 * * * * * * * * * * *
	 * @author ddm
	 * @date 2017年7月31日-下午2:31:30
	 */
	public static RegistDeviceResponse addDevice(String productKey,String deviceName)throws Exception {
		RegistDeviceRequest request = new RegistDeviceRequest();
		request.setProductKey(productKey);	// 产品 
		request.setDeviceName(deviceName);// 可以设空，如果名称为空则由阿里云生成设备名称默认与设备id一致。设备名称在产品内唯一，如果已存在则返回已有设备
		RegistDeviceResponse resp = client.getAcsResponse(request);
		
		return resp;
	}

	/**
	 * 发送广播消息
	 * @title null
	 * * * * * * * * * * *
	 * @author ddm
	 * @date 2017年7月31日-下午3:22:46
	 * @param 
	 * @retrun {"result":"1"} #ok {"result":"0"} #error
	 */
	public static SendResultData send(String productKey,String topicName,String msg)throws Exception{
		PubBroadcastRequest request = new PubBroadcastRequest();
		request.setProductKey(productKey);
		request.setMessageContent(Base64Util.encodeData(msg)); //Hello world base64 String
		request.setTopicFullName("/broadcast/"+productKey+"/"+topicName); //消息发送到的Topic
		PubBroadcastResponse response = client.getAcsResponse(request);
		
		SendResultData res = new SendResultData();
		
		res.setRequestId(response.getRequestId());		//当前请求的ID
		res.setSuccess(response.getSuccess());			//请求是否成功
		res.setErrorMessage(response.getErrorMessage());//出错时的错误信息
		
		return res;
	}
	
	/**
	 * 同步发送数据
	 * @title null
	 * * * * * * * * * * *
	 * @author ddm
	 * @date 2017年7月31日-下午3:17:14
	 * @param 
	 * @retrun {"result":"1"} #ok {"result":"0"} #error
	 */
	public static SendResultData sendByRRpc(String productKey,String deviceName,String msg,Integer timeOut)throws Exception{
		RRpcRequest rrpcRequest = new RRpcRequest();
		rrpcRequest.setProductKey(productKey); //设备所属产品的Key
		rrpcRequest.setDeviceName(deviceName); //设备名称
		rrpcRequest.setRequestBase64Byte(Base64Util.encodeData(msg)); //发给设备的数据，要求二进制数据做一次Base64编码
		if(null == timeOut){
			rrpcRequest.setTimeout(1000); 	 //超时时间，单位毫秒，如果超过这个时间设备没反应则返回"TIMEOUT"
		}else{
			rrpcRequest.setTimeout(timeOut); //超时时间，单位毫秒，如果超过这个时间设备没反应则返回"TIMEOUT"
		}
		
		RRpcResponse rrpcResponse = client.getAcsResponse(rrpcRequest);
		
		SendResultData res = new SendResultData();
		
		res.setRequestId(rrpcResponse.getRequestId());
		res.setSuccess(rrpcResponse.getSuccess());
		res.setMessageId(rrpcResponse.getMessageId());
		res.setErrorMessage(rrpcResponse.getErrorMessage());
		res.setPrpcCode(rrpcResponse.getRrpcCode());// 返回调用码
		res.setPayloadBase64Byte(Base64Util.getFromBASE64(rrpcResponse.getPayloadBase64Byte()));// 返回的值
		
		return res;
	}
	
	/**
	 * 发送数据 到  topIc
	 * 
	 * @return
	 */
	public static SendResultData sendByTopic(String productKey, String pubTopic, String msg, Integer qos) throws Exception {

		// 发送对象
		PubRequest request = new PubRequest();
		// 设备参数值
		request.setProductKey(productKey); // 产品
		request.setMessageContent(Base64Util.encodeData(msg)); // 消息
		request.setTopicFullName(pubTopic); // 发布地址
		//
		request.setQos(qos); // 目前支持QoS0和QoS1

		PubResponse response = client.getAcsResponse(request); // 返回 值对象

		SendResultData res = new SendResultData();
		res.setSuccess(response.getSuccess());
		res.setRequestId(response.getRequestId());
		res.setErrorMessage(response.getErrorMessage());
		
		return res;
	}

	/**
	 * 批量注册设备 并返回注册结果
	 * @param devices
	 * @param productKey
	 * @return
	 */
	public static List<ApplyDeviceInfo> batchRegDeviceAll(List<String> devices, String productKey) {
		// 注册
		Long applyId = batchRegDevice(devices, productKey);
		// 判断是否注册成功
		if (null != applyId) {
			try {
				// 判断是否注册完成
				while (!selectBatchRegStatus(applyId)) {
					Thread.sleep(1000);
				}
				// 返回注册结果
				return selectBatchRegInfo(applyId, devices.size(), 1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 批量注册设备
	 * 
	 * @param devices
	 *            设备列表
	 * @param productKey
	 *            产品 Key
	 * @return
	 */
	public static Long batchRegDevice(List<String> devices, String productKey) {
		ApplyDeviceWithNamesRequest request = new ApplyDeviceWithNamesRequest();
		/*
		 * List<String> devices = new ArrayList<String>();
		 * devices.add("device_a"); devices.add("device_b");
		 * devices.add("device_c"); devices.add("device01");
		 */
		request.setProductKey(productKey);
		request.setDeviceNames(devices);
		ApplyDeviceWithNamesResponse response = null;
		try {
			response = client.getAcsResponse(request);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		if (response != null) {
			System.out.println("Response requestId:" + response.getRequestId() + " isSuccess:" + response.getSuccess()
					+ " Error:" + response.getErrorMessage());

			if (response.getSuccess()) {
				return response.getApplyId();
			}

		}

		return null;
	}

	/**
	 * 判断批量申请状态
	 * 
	 * @param applyId
	 * @return
	 */
	public static boolean selectBatchRegStatus(Long applyId) {
		QueryApplyStatusRequest request = new QueryApplyStatusRequest();
		request.setApplyId(applyId);
		QueryApplyStatusResponse response = null;
		try {
			response = client.getAcsResponse(request);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		if (response != null) {
			System.out.println("Response requestId:" + response.getRequestId() + " isSuccess:" + response.getSuccess()
					+ " Error:" + response.getErrorMessage());
			if (response.getSuccess()) {
				return response.getFinish();
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * 获取批量注册的 设备信息
	 * 
	 * @param applyId
	 * @param pageSize
	 * @param currPage
	 * @return
	 */
	public static List<ApplyDeviceInfo> selectBatchRegInfo(Long applyId, Integer pageSize, Integer currPage) {
		QueryPageByApplyIdRequest request = new QueryPageByApplyIdRequest();
		request.setApplyId(applyId);
		request.setCurrentPage(currPage);
		request.setPageSize(pageSize);
		QueryPageByApplyIdResponse response = null;
		try {
			response = client.getAcsResponse(request);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		if (response != null) {
			System.out.println("Response requestId:" + response.getRequestId() + " isSuccess:" + response.getSuccess()
					+ " Error:" + response.getErrorMessage());
			if (response.getSuccess()) {
				List<ApplyDeviceInfo> infos = response.getApplyDeviceList();
				return infos;
			}
		}
		return null;
	}
	
}
