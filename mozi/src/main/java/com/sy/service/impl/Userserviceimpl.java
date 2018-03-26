package com.sy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sy.mapper.UserMapper;
import com.sy.pojo.Management;
import com.sy.pojo.User;
import com.sy.service.Userservice;
import com.sy.utils.MD5Util;
import com.sy.utils.PageModel;
@Service
public class Userserviceimpl implements Userservice {
	@Value("#{configProperties['jdbc.server']}")
	private String baseUrl;
	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean addUser(User u) {
		u.setCreatetime(new Date());
		u.setAtlasttime(new Date());
		String p=u.getPassword();
		u.setPassword(MD5Util.MD5(p));
		int num = userMapper.insertSelective(u);
		 if (num != 0) {
				return true;
			} else {
				return false;
			}
	}

	@Override
	public boolean ifUser(String account) {
		User u = userMapper.ifUser(account);
		if(u==null){
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public User landingUser(String account, String password) {
		// TODO Auto-generated method stub
		password = MD5Util.MD5(password);
		User lu = new User();
		lu.setPassword(password);
		lu.setAccount(account);
		User u =userMapper.landingUser(lu);
		if(u !=null){
			u.setAtlasttime(new Date());
			userMapper.updateByPrimaryKeySelective(u);
		}
		return u ;
	}

	@Override
	public boolean uploadavatar(String avatar, Integer id) {
		User u = new User();
		u.setAvatar(baseUrl+avatar);
		u.setId(id);
		Integer num =userMapper.updateByPrimaryKeySelective(u);
		 if (num != 0) {
				return true;
			} else {
				return false;
			}
	}

	@Override
	public User getuserid(Integer id) {
		if(userMapper ==null ){
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
            userMapper=(UserMapper)webApplicationContext.getBean("userMapper");
		}
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateUser(User u) {
		u.setPassword(MD5Util.MD5(u.getPassword()));
		Integer num =userMapper.updateByPrimaryKeySelective(u);
		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updatepassword(String password, String newpassword,Integer id) {
	
		password = MD5Util.MD5(password);
		newpassword= MD5Util.MD5(newpassword);
		Map m= new HashMap();
		m.put("password", password);
		m.put("id", id);
		User olp = userMapper.getpassword(m);
		if(olp != null){
			olp.setPassword(newpassword);
			userMapper.updateByPrimaryKeySelective(olp);
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public PageModel<User> getusersone(Integer pageNo, String keyWord) {
		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		 //获取数据总数
		    Integer count=userMapper.getcount(keyWord);
		    Integer pageSize=10;
		    List<User>Feedbacks = null;
		    Integer pageNo1 = ( pageNo - 1) * pageSize;
		    //获取页数
		    HashMap<String, Object> parameter = new HashMap<>();
		    parameter.put("pageNo", pageNo1);
		    parameter.put("keyWord", keyWord);
		    parameter.put("pageSize", pageSize);
		    Feedbacks = userMapper.list(parameter);
		    
		    PageModel<User> pageModel = new PageModel<User>(pageNo, pageSize,count, Feedbacks,"user/list");
		if(pageModel.getCount() !=0){
			pageModel.init();
		}
		return pageModel;
	}
	
	
}
