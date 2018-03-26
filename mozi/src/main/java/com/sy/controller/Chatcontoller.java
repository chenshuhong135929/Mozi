package com.sy.controller;

import io.netty.channel.Channel;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.common.ResultBase;
import com.sy.nettyulit.NettyChannelMap;

/**用户发送信息接口
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "chat", method = RequestMethod.POST)
public class Chatcontoller {
	
	@RequestMapping(value="sendMessage")
	@ResponseBody
	public ResultBase sendMessage(@RequestBody Map m ){
		ResultBase re = new ResultBase();
		try {
			Channel c =	NettyChannelMap.get((String)m.get("imei"));
			c.writeAndFlush((String)m.get("message"));
			re.setCode(200);
			re.setMessage("发送信息成功！！！");
		} catch (Exception e) {
			re.setCode(200);
			re.setMessage("发送信息失败！！！");
		}
		return re;
	}
}
