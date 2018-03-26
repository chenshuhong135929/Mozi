package com.sy.iot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.sy.utils.Config;

public class IotBase {
	protected static String accessKey;	// 访问键
	protected static String accessSecret;	// 访问密钥
	
	protected static String profileStr;	// 节点
	
	protected static Config config = null;	// 配置对像
	
	protected static IClientProfile profile = null;	// 节点对象
	protected static DefaultAcsClient client = null;	// SDK 客户端
	// 日志对象
	protected static final Logger logger = LoggerFactory.getLogger(IotBase.class);
	static{
		config = new Config("iotConfig.properties");
		accessKey = config.getString("accessKey").trim();
		accessSecret = config.getString("accessSecret").trim();
		profileStr = config.getString("profile").trim();
		
		try {
			DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot",
					"iot.cn-shanghai.aliyuncs.com");
			profile = DefaultProfile.getProfile(profileStr,
					accessKey, accessSecret);	// 初始化 节点
			
			client = new DefaultAcsClient(profile); // 初始化SDK客户端
		} catch (ClientException e) {
			e.printStackTrace();
			logger.info("初始化 SDK 客户端异常",e);
		}
	}
}
