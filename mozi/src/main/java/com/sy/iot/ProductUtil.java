package com.sy.iot;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20170620.CreateProductRequest;
import com.aliyuncs.iot.model.v20170620.CreateProductResponse;
import com.aliyuncs.iot.model.v20170620.CreateProductResponse.ProductInfo;

/**
 * 产品
 * 
 * @className Product.java
 * @description: TODO
 * @author ddm
 * @date 2017年7月29日-上午9:14:04
 */
public class ProductUtil extends IotBase {
	private ProductUtil(){}
	
	
	/**
	 * 产品注册
	 * @title null
	 * * * * * * * * * * *
	 * @author ddm
	 * @date 2017年7月29日-上午11:41:51
	 * @param
	 * 	productName 产品名称
	 * 	catId id  可空
	 *  des 描述 可空
	 *  securityPolicy 安全策略  可空
	 * @retrun {"result":"1"} #ok {"result":"0"} #error
	 */
	public static ProductInfo regProduct(String productName, Long catId, String des, String securityPolicy)throws Exception {
		if(null == productName|| "".equals(productName.trim())){
			throw new Exception("产品名称不能为空");
		}
		CreateProductRequest request = new CreateProductRequest();
		request.setCatId(catId);		// id
		request.setDesc(des);			// 描述
		request.setName(productName);	// 产品名称
		request.setSecurityPolicy(securityPolicy);	// 安全策略
		
		CreateProductResponse response = null;
		try {
			response = client.getAcsResponse(request);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		if (response != null) {
			if (response.getSuccess()) {
				logger.debug("注册产品成功:Response requestId:" + response.getRequestId() + " isSuccess:"
						+ response.getSuccess() + " Error:" + response.getErrorMessage());
				return response.getProductInfo();
			} else {
				logger.error("注册产品失败：" + response.getErrorMessage());
				return null;
			}
			
		} else {
			logger.error("注册产品异常!");
			return null;
		}
	}

}
