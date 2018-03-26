package com.sy.iot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import com.sy.utils.Config;
import com.sy.utils.Base64Util;

/**
 * 队列访问  用户接收数据
 * 
 * @author 邓代木
 *
 */
public class QueueAccess implements Runnable {
	// 配置文件对象
	private static Config config = null;

	private String accessKey; // 访问键
	private String accessSecret; // 访问密钥
	private String endpoing; // 接收地址
	private String queueName; // 队列名
	// 日志对象
	private static final Logger logger = LoggerFactory.getLogger(Base64Util.class);

	public QueueAccess() {
	}

	/**
	 * 构造对象
	 * 
	 * @param accessKey
	 *            访问键
	 * @param accessSecret
	 *            访问密钥
	 * @param endpoing
	 *            接收网址
	 * @param queueName
	 *            队列名
	 */
	public QueueAccess(String accessKey, String accessSecret, String endpoing, String queueName) {
		this.accessKey = accessKey.trim();
		this.accessSecret = accessSecret.trim();
		this.endpoing = endpoing.trim();
		this.queueName = queueName.trim();
	}

	/**
	 * 初始化队列
	 */
	public void initQueue() {
		try {
			if (null == accessKey || null == accessSecret || null == endpoing || null == queueName) {
				System.out.println("初始化失败!");
				logger.info("队列初始化失败!键，密钥，endpoing,队列名不能为空！");
			} else if ("".equals(accessKey) || "".equals(accessSecret) || "".equals(endpoing) || "".equals(queueName)) {
				System.out.println("初始化失败!");
				logger.info("队列初始化失败!键，密钥，endpoing,队列名不能为空！");
			} else {
				Thread th = new Thread(new QueueAccess(accessKey, accessSecret, endpoing, queueName));
				th.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("队列消息接收器启动失败!", e);
		}
	}

	/**
	 * 启动
	 */
	@Override
	public void run() {
		if (null == accessKey || null == accessSecret || null == endpoing || null == queueName) {
			System.out.println("初始化失败!");
			logger.info("队列初始化失败!键，密钥，endpoing,队列名不能为空！");
		} else if ("".equals(accessKey) || "".equals(accessSecret) || "".equals(endpoing) || "".equals(queueName)) {
			System.out.println("初始化失败!");
			logger.info("队列初始化失败!键，密钥，endpoing,队列名不能为空！");
		} else {
			receive();
		}
	}

	/**
	 * 接收消息
	 * 
	 * @param queue
	 */
	private void receive() {
		// 访问对象
		CloudAccount account = new CloudAccount(accessKey, accessSecret, endpoing);
		// 队列对象
		MNSClient client = account.getMNSClient();
		//
		CloudQueue queue = client.getQueueRef(queueName); // 参数请输入IoT自动创建的队列名称

		while (true) {
			// 获取消息
			Message popMsg = queue.popMessage(10); // 长轮询等待时间为10秒
			if (popMsg != null) {
				System.out.println("PopMessage Body: " + popMsg.getMessageBodyAsRawString()); // 获取原始消息
				System.out.println("内容：" + popMsg.getMessageBody());
				try {
					// 解析数据
					QueueAnalysis.analysis(popMsg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				queue.deleteMessage(popMsg.getReceiptHandle()); // 从队列中删除消息
			} else {
				System.out.println("Continuing");
			}
		}
	}

	/**
	 * 访问键
	 * 
	 * @return
	 */
	public String getAccessKey() {
		return accessKey;
	}

	/**
	 * 访问键
	 * 
	 * @param accessKey
	 */
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	/**
	 * 访问密钥
	 * 
	 * @return
	 */
	public String getAccessSecret() {
		return accessSecret;
	}

	/**
	 * 访问密钥
	 * 
	 * @param accessSecret
	 */
	public void setAccessSecret(String accessSecret) {
		this.accessSecret = accessSecret;
	}

	/**
	 * 接收网址
	 * 
	 * @return
	 */
	public String getEndpoing() {
		return endpoing;
	}

	/**
	 * 接收网址
	 * 
	 * @param endpoing
	 */
	public void setEndpoing(String endpoing) {
		this.endpoing = endpoing;
	}

	/**
	 * 队列名
	 * 
	 * @return
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * 队列名
	 * 
	 * @param queueName
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

}
