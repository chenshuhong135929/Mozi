package com.sy.common;

/**
 * 执行结果
 * 
 * @author 帝血弑天——DDM——
 *
 */
public class ResultData<T> extends ResultBase {

	private T data;// 数据

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
