package com.sy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.Clock;
import com.sy.service.Clockservice;

@Controller
@RequestMapping(value = "clock", method = RequestMethod.POST)
public class Clockcontooler {
	@Autowired
	private Clockservice clockservice;
	@RequestMapping(value="addClock")
	@ResponseBody
	public ResultBase addClock(@RequestBody Clock c ){
		ResultBase re = new ResultBase();
		boolean status = clockservice.addClock(c);
		if(status){
			re.setCode(200);
			re.setMessage("添加闹钟成功！！！");
		}else {
			re.setCode(400);
			re.setMessage("添加闹钟失败！！！");
		}
		
		return re;
	}
	
	@RequestMapping(value="updateClock")
	@ResponseBody
	public ResultBase updateClock(@RequestBody Clock c ){
		ResultBase re = new ResultBase();
		boolean status = clockservice.updateClock(c);
		if(status){
			re.setCode(200);
			re.setMessage("修改闹钟成功！！！");
		}else {
			re.setCode(400);
			re.setMessage("修改闹钟失败！！！");
		}
		
		return re;
	}
	
	@RequestMapping(value="deleteClock")
	@ResponseBody
	public ResultBase deleteClock(@RequestBody Map m ){
		ResultBase re = new ResultBase();
		boolean status = clockservice.deleteClock(Integer.parseInt((String)m.get("id")));
		if(status){
			re.setCode(200);
			re.setMessage("删除闹钟成功！！！");
		}else {
			re.setCode(400);
			re.setMessage("删除闹钟失败！！！");
		}
		
		return re;
	}
	
	@RequestMapping(value="selectclocks")
	@ResponseBody
	public ResultData<List<Clock> > selectclocks(@RequestBody Map m ){
		ResultData<List<Clock> > re = new ResultData<List<Clock> >();
		List<Clock> cs= clockservice.selectclocks((String)m.get("imei"));
		if(cs.size() >0){
			re.setCode(200);
			re.setMessage("获取闹钟数据成功！！！");
			re.setData(cs );
		}else {
			re.setCode(350);
			re.setMessage("获取闹钟数据失败！！！");
		}
		
		return re;
	}
	
	
}
