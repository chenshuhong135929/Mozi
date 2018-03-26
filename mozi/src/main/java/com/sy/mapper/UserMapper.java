package com.sy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.User;
import com.sy.pojo.UserExample;

public interface UserMapper {
	int countByExample(UserExample example);

	int deleteByExample(UserExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	List<User> selectByExample(UserExample example);

	User selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") User record,
			@Param("example") UserExample example);

	int updateByExample(@Param("record") User record,
			@Param("example") UserExample example);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	public User ifUser(String account);

	public User landingUser(User u);

	public User getpassword(Map m);

	public Integer getcount(String keyWord);

	public List<User> list(Map map);

}