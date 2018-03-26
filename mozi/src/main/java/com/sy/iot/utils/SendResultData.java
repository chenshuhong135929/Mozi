package com.sy.iot.utils;

/**
 * 发送返回的数据
 * @className 
 * 		SendResultData.java
 * @description: TODO
 * @author ddm
 * @date 2017年7月31日-下午3:14:19
 */
public class SendResultData {
	private String requestId;
	private String errorMessage;
	private Boolean success;

	private String prpcCode; // 表示调用返回码
	/**
	 * UNKNOW:系统异常 
	 * SUCCESS:成功 
	 * TIMEOUT:设备响应超时 OFFLINE: 设备离线 HALFCONN:
	 * 设备离线(设备连接断开但是断开时间未超过一个心跳周期)
	 */
	private String payloadBase64Byte; // 设备返回的二进制数据Base64编码后的值
	private Long messageId; // 消息 id
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getPrpcCode() {
		return prpcCode;
	}
	public void setPrpcCode(String prpcCode) {
		this.prpcCode = prpcCode;
	}
	public String getPayloadBase64Byte() {
		return payloadBase64Byte;
	}
	public void setPayloadBase64Byte(String payloadBase64Byte) {
		this.payloadBase64Byte = payloadBase64Byte;
	}
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	
}
