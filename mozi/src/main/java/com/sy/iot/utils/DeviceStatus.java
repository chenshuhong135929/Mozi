package com.sy.iot.utils;

import java.util.Date;

/**
 * 设备状态变化
 * 
 * @className DeviceStatus.java
 * @description: TODO
 * @author ddm
 * @date 2017年7月31日-下午8:19:05
 */
public class DeviceStatus {
	private String productKey;
	private Date lastTime;
	private Date time;
	private String deviceName;
	private String status;
	public String getProductKey() {
		return productKey;
	}
	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
