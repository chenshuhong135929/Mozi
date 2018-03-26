package com.sy.iot.utils;

import java.util.Date;

/**
 * 消息数据
 * @author Administrator
 *
 */
public class IotData {
	private String type;	// 类型
	private String data;	// 数据
	private Date createTime;// 创建时间
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
